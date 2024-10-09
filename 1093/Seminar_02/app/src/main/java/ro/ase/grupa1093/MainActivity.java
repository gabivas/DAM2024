package ro.ase.grupa1093;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Spinner spnSex;
    Button btnAfisare;
    EditText etTelefon;

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

        etTelefon = findViewById(R.id.etTelefon);
        spnSex = findViewById(R.id.spnSex);
        btnAfisare = findViewById(R.id.btnAfisare);

        String[] listaValori = {"MASCULIN", "FEMININ"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaValori);
        spnSex.setAdapter(adapter);

        btnAfisare.setOnClickListener(view -> {
            Toast.makeText(this, etTelefon.getText().toString() + spnSex.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 1, "Adauga telefon");
        menu.add(0, 2, 2, "Descriere telefon");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1: {
                Toast.makeText(this, "Adauga telefon", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }
}