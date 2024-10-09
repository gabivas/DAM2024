package ro.ase.grupa1094;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    enum Gen {
        MASCULIN, FEMININ
    }

    Button btnAfisare;
    EditText etUtilizator;
    Spinner spnGen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etUtilizator = findViewById(R.id.etUtilizator);
        btnAfisare = findViewById(R.id.btnAfisare);
        spnGen = findViewById(R.id.spnGen);
        String[] valoriGen = new String[Gen.values().length];
        int nrValori = 0;
        for (Gen gen : Gen.values()) {
            valoriGen[nrValori++] = gen.toString();
        }
        ArrayAdapter<String> genAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, valoriGen);
        spnGen.setAdapter(genAdapter);

        btnAfisare.setOnClickListener(view -> {
            Toast.makeText(this, etUtilizator.getText().toString(), Toast.LENGTH_LONG).show();
        });

    }
}