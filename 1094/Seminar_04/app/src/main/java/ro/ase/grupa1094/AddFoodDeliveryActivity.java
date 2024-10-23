package ro.ase.grupa1094;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddFoodDeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_food_delivery);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText etNume = findViewById(R.id.etNume);
        EditText etCantitate = findViewById(R.id.etCantitate);
        EditText etPret = findViewById(R.id.etPret);
        EditText etAdresa = findViewById(R.id.etAdresa);
        EditText etDataLivrare = findViewById(R.id.etDataLivrare);
        Spinner spnProduse = findViewById(R.id.spnProduse);
        RadioGroup rgValuta = findViewById(R.id.rgValuta);
        Button btnSave = findViewById(R.id.btnSalveaza);

        btnSave.setOnClickListener(view -> {
            String nume = etNume.getText().toString();
            String adresa = etAdresa.getText().toString();
            int cantitate = Integer.parseInt(etCantitate.getText().toString());
            float pret = Float.parseFloat(etPret.getText().toString());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataLivrare = null;
            try {
                dataLivrare = sdf.parse(etDataLivrare.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            Produse produse = Produse.valueOf(spnProduse.getSelectedItem().toString());
            RadioButton rbCheckedValue = findViewById(rgValuta.getCheckedRadioButtonId());
            Valuta valuta = Valuta.valueOf(rbCheckedValue.getText().toString());

            Food food = new Food(nume, pret, cantitate, adresa, dataLivrare, produse, valuta);
            //Toast.makeText(this, food.toString(), Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
            intent.putExtra("foodForIntent", food);

            setResult(RESULT_OK, intent);
            finish();
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.produse, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        spnProduse.setAdapter(adapter);
    }
}