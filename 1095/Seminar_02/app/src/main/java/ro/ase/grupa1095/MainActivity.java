package ro.ase.grupa1095;

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
    private static final int GROUP_ID = 0;
    public static final int ID_OPTIUNE1 = 1;
    public static final int ID_OPTIUNE2 = 2;
    EditText etEmail;
    Button btnAfisare;
    Spinner spnSpecializare;

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

        etEmail = findViewById(R.id.etEmail);
        btnAfisare = findViewById(R.id.btnAfisare);
        spnSpecializare = findViewById(R.id.spnSpecializare);

        String[] specializari = {"Informatica", "Cibernetica"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, specializari);
        spnSpecializare.setAdapter(adapter);

        btnAfisare.setOnClickListener(view -> {
            Toast.makeText(this, etEmail.getText().toString(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(GROUP_ID, ID_OPTIUNE1, 1, R.string.optiune_1);
        menu.add(GROUP_ID, ID_OPTIUNE2, 2, R.string.optiune_2);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case ID_OPTIUNE1:
                Toast.makeText(this, "Am apasat optiune 1", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}