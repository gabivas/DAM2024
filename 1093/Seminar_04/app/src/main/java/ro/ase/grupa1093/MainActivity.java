package ro.ase.grupa1093;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabDeschideActivitateNoua;
    private ListView lvAnimale;
    List<AnimalZoo> animale = new ArrayList<>();
    ActivityResultLauncher<Intent> launcher;


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

        lvAnimale = findViewById(R.id.lvAnimale);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();
                AnimalZoo animalZoo = (AnimalZoo) intent.getSerializableExtra("animalZooFromIntent");
                if (animalZoo != null) {
                    animale.add(animalZoo);
                }
                ArrayAdapter<AnimalZoo> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, animale);
                lvAnimale.setAdapter(adapter);
            }
        });

        fabDeschideActivitateNoua = findViewById(R.id.fabDeschide);
        fabDeschideActivitateNoua.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdaugaAnimalZooActivity.class);
            //startActivity(intent);
            launcher.launch(intent);
        });

    }

}