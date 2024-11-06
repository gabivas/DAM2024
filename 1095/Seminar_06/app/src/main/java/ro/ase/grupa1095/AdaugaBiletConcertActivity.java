package ro.ase.grupa1095;

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

public class AdaugaBiletConcertActivity extends AppCompatActivity {
    private Spinner spnCategoriiBilete;
    private boolean isEditing = false;

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

        Intent editIntent = getIntent();
        if (editIntent.hasExtra("edit")) {
            isEditing = true;
            Bilet editBilet = (Bilet) editIntent.getSerializableExtra("edit");
            //Toast.makeText(this, editBilet.toString(), Toast.LENGTH_SHORT).show();
            etNume.setText(editBilet.getNume());
            etLocatie.setText(editBilet.getLocatie());
            etNrBilete.setText(String.valueOf(editBilet.getNrBilete()));
            etDataConcert.setText(new SimpleDateFormat("dd.MM.yyyy").format(editBilet.getDataConcert()));
            ArrayAdapter<String> spnAdapter = (ArrayAdapter<String>) spnCategoriiBilete.getAdapter();
            for (int i = 0; i < spnAdapter.getCount(); i++) {
                if (editBilet.getCategorie().toString().equals(spnAdapter.getItem(i))) {
                    spnCategoriiBilete.setSelection(i);
                }
            }

            switch (editBilet.getMetodaPlata().toString()) {
                case "CARD": {
                    rgMetodaPlata.check(R.id.rbCard);
                    break;
                }
                case "CASH": {
                    rgMetodaPlata.check(R.id.rbCash);
                    break;
                }
            }
        }

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.LOCAL), MODE_PRIVATE);

        String token = sharedPreferences.getString("token", "Default");
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();

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
            if (isEditing) {
                intent.putExtra("edit", bilet);
                isEditing = false;
            } else {
                intent.putExtra("biletFromIntent", bilet);
            }

            setResult(RESULT_OK, intent);
            finish();
        });

    }
}