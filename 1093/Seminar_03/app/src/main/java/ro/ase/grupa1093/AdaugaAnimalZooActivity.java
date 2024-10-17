package ro.ase.grupa1093;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
    }
}