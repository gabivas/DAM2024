package ro.ase.grupa1095;

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

public class AdaugaBiletConcertActivity extends AppCompatActivity {
    private Spinner spnCategoriiBilete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_bilet_concert);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spnCategoriiBilete = findViewById(R.id.spnCategorie);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoriiBilete, android.R.layout.simple_spinner_dropdown_item);
        spnCategoriiBilete.setAdapter(adapter);

        EditText etNume = findViewById(R.id.etNume);
        EditText etNrBilete = findViewById(R.id.etNrBilete);
        EditText etLocatie = findViewById(R.id.etLocatie);
        EditText etDataConcert = findViewById(R.id.etDataConcert);
        RadioGroup rgMetodaPlata = findViewById(R.id.rgMetodaPlata);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        btnSalveaza.setOnClickListener(view -> {
            String nume = etNume.getText().toString();
            String locatie = etLocatie.getText().toString();
            int nrBilete = Integer.parseInt(etNrBilete.getText().toString());
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date dataConcert = null;
            try {
                dataConcert = sdf.parse(etDataConcert.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Categorie categorie = Categorie.valueOf(spnCategoriiBilete.getSelectedItem().toString());
            RadioButton rbMetodaPlataSelectata = findViewById(rgMetodaPlata.getCheckedRadioButtonId());
            MetodaPlata metodaPlata = MetodaPlata.valueOf(rbMetodaPlataSelectata.getText().toString());

            Bilet bilet = new Bilet(nume, locatie, nrBilete, dataConcert, metodaPlata, categorie);
            //Toast.makeText(this, bilet.toString(), Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
            intent.putExtra("biletFromIntent", bilet);

            setResult(RESULT_OK, intent);
            finish();
        });

    }
}