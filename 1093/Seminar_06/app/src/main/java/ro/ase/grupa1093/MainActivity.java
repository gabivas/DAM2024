package ro.ase.grupa1093;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private int position;


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

        lvAnimale.setOnItemClickListener((adapterView, view, position, l) -> {
            this.position = position;
            Intent intent = new Intent(getApplicationContext(), AdaugaAnimalZooActivity.class);
            intent.putExtra("edit", animale.get(position));
            launcher.launch(intent);
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getData().hasExtra("animalZooFromIntent")) {
                Intent intent = result.getData();
                AnimalZoo animalZoo = (AnimalZoo) intent.getSerializableExtra("animalZooFromIntent");
                if (animalZoo != null) {
                    animale.add(animalZoo);
                }
                //ArrayAdapter<AnimalZoo> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, animale);
                AnimalZooAdapter adapter = new AnimalZooAdapter(getApplicationContext(), R.layout.view_animalzoo, animale, getLayoutInflater());
                lvAnimale.setAdapter(adapter);
            } else if (result.getData().hasExtra("edit")) {
                Intent intent = result.getData();
                AnimalZoo editedAnimalZoo = (AnimalZoo) intent.getSerializableExtra("edit");
                if (editedAnimalZoo != null) {
                    AnimalZoo animalZoo = animale.get(position);

                    animalZoo.setSpecie(editedAnimalZoo.getSpecie());
                    animalZoo.setGreutate(editedAnimalZoo.getGreutate());
                    animalZoo.setSex(editedAnimalZoo.getSex());
                    animalZoo.setDataNastere(editedAnimalZoo.getDataNastere());
                    animalZoo.setVarsta(editedAnimalZoo.getVarsta());
                    animalZoo.setTaraOrigine(editedAnimalZoo.getTaraOrigine());
                    animalZoo.setStilAlimentar(editedAnimalZoo.getStilAlimentar());
                    AnimalZooAdapter adapter = (AnimalZooAdapter) lvAnimale.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("local", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", "Token1234");
        editor.apply();


        fabDeschideActivitateNoua = findViewById(R.id.fabDeschide);
        fabDeschideActivitateNoua.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdaugaAnimalZooActivity.class);
            //startActivity(intent);
            launcher.launch(intent);
        });

    }

}