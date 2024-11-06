package ro.ase.grupa1094;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private boolean isEditing = false;

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.produse, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        spnProduse.setAdapter(adapter);

        Intent editIntent = getIntent();
        if (editIntent.hasExtra("edit")) {
            isEditing = true;
            Food editFood = (Food) editIntent.getSerializableExtra("edit");
            //Toast.makeText(this, editFood.toString(), Toast.LENGTH_SHORT).show();
            etNume.setText(editFood.getNume());
            etAdresa.setText(editFood.getAdresa());
            etCantitate.setText(String.valueOf(editFood.getCantitate()));
            etPret.setText(String.valueOf(editFood.getPret()));
            etDataLivrare.setText(new SimpleDateFormat("dd/MM/yyyy").format(editFood.getDataLivrare()));
            ArrayAdapter<String> editedAdapter = (ArrayAdapter<String>) spnProduse.getAdapter();
            for (int i = 0; i < editedAdapter.getCount(); i++) {
                if (editFood.getProduse().toString().equals(editedAdapter.getItem(i))) {
                    spnProduse.setSelection(i);
                }
            }
            switch (editFood.getValuta().toString()) {
                case "RON": {
                    rgValuta.check(R.id.rbRon);
                    break;
                }
                case "EUR": {
                    rgValuta.check(R.id.rbEur);
                    break;
                }
            }

        }
        SharedPreferences sharedPreferences = getSharedPreferences("local", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "Default");
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();

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

            if (isEditing) {
                intent.putExtra("edit", food);
                isEditing = false;

            } else {
                intent.putExtra("foodForIntent", food);
            }
            setResult(RESULT_OK, intent);
            finish();
        });

    }
}