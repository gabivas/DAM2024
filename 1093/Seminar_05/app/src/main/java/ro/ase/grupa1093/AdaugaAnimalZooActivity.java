package ro.ase.grupa1093;

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

public class AdaugaAnimalZooActivity extends AppCompatActivity {
    private Spinner spnStiluriAlimentare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_animal_zoo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spnStiluriAlimentare = findViewById(R.id.spnStilAlimentar);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.stilAlimentar, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spnStiluriAlimentare.setAdapter(adapter);

        EditText etSpecie = findViewById(R.id.etSpecie);
        EditText etGreutate = findViewById(R.id.etGreutate);
        EditText etVarsta = findViewById(R.id.etVarsta);
        EditText etTaraOrigine = findViewById(R.id.etTaraOrigine);
        EditText etDataNastere = findViewById(R.id.etDataNastere);
        RadioGroup rgSex = findViewById(R.id.rgSex);
        Button btnSalveaza = findViewById(R.id.btnSalveazaAnimal);

        Intent editIntent = getIntent();
        if (editIntent.hasExtra("edit")) {
            AnimalZoo animalZoo = (AnimalZoo) editIntent.getSerializableExtra("edit");
            //Toast.makeText(this, animalZoo.toString(), Toast.LENGTH_SHORT).show();
            etSpecie.setText(animalZoo.getSpecie());
        }

        btnSalveaza.setOnClickListener(view -> {
            String specie = etSpecie.getText().toString();
            float greutate = Float.parseFloat(etGreutate.getText().toString());
            int varsta = Integer.parseInt(etVarsta.getText().toString());
            String taraOrigine = etTaraOrigine.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNastere = null;
            try {
                dataNastere = sdf.parse(etDataNastere.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            StilAlimentar stilAlimentar = StilAlimentar.valueOf(spnStiluriAlimentare.getSelectedItem().toString());
            RadioButton rbSexSelectat = findViewById(rgSex.getCheckedRadioButtonId());
            Sex sex = Sex.valueOf(rbSexSelectat.getText().toString());
            AnimalZoo animalZoo = new AnimalZoo(specie, greutate, varsta, taraOrigine, dataNastere, stilAlimentar, sex);
            //Toast.makeText(this, animalZoo.toString(), Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
            intent.putExtra("animalZooFromIntent", animalZoo);

            setResult(RESULT_OK, intent);
            finish();
        });

    }
}