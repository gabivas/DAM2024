package ro.ase.grupa1095;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
    private FloatingActionButton fabDeschideActivitate;
    private ListView lvListaBilete;
    private List<Bilet> listaBilete = new ArrayList<>();
    private int pozitieBiletEditatInLista;

    private ActivityResultLauncher<Intent> launcher;

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

        lvListaBilete = findViewById(R.id.lvListaBilete);

        lvListaBilete.setOnItemClickListener((adapterView, view, position, l) -> {
            pozitieBiletEditatInLista = position;
            Intent intent = new Intent(getApplicationContext(), AdaugaBiletConcertActivity.class);
            intent.putExtra("edit", listaBilete.get(position));
            launcher.launch(intent);
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getData().hasExtra("biletFromIntent")) {
                Intent intent = result.getData();
                Bilet bilet = (Bilet) intent.getSerializableExtra("biletFromIntent");
                //Toast.makeText(this, bilet.toString(), Toast.LENGTH_SHORT).show();
                if (bilet != null) {
                    listaBilete.add(bilet);
                    //ArrayAdapter<Bilet> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listaBilete);
                    BiletAdapter adapter = new BiletAdapter(getApplicationContext(), R.layout.view_bilete, listaBilete, getLayoutInflater());
                    lvListaBilete.setAdapter(adapter);
                }
            } else if (result.getData().hasExtra("edit")) {
                Intent intent = result.getData();
                Bilet bilet = (Bilet) intent.getSerializableExtra("edit");
                //Toast.makeText(this, bilet.toString(), Toast.LENGTH_SHORT).show();
                if (bilet != null) {
                    Bilet biletDeActualizat = listaBilete.get(pozitieBiletEditatInLista);
                    biletDeActualizat.setNume(bilet.getNume());
                    biletDeActualizat.setLocatie(bilet.getLocatie());
                    biletDeActualizat.setNrBilete(bilet.getNrBilete());
                    biletDeActualizat.setDataConcert(bilet.getDataConcert());
                    biletDeActualizat.setCategorie(bilet.getCategorie());
                    biletDeActualizat.setMetodaPlata(bilet.getMetodaPlata());
                    BiletAdapter biletAdapter = (BiletAdapter) lvListaBilete.getAdapter();
                    biletAdapter.notifyDataSetChanged();
                }

            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.LOCAL), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", "123Token");
        editor.apply();

        String token = sharedPreferences.getString("token", "TokenDefault");
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();

        fabDeschideActivitate = findViewById(R.id.fabDeschide);
        fabDeschideActivitate.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdaugaBiletConcertActivity.class);
            //startActivity(intent);
            launcher.launch(intent);
        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.meniu, menu);
//        return true;
//    }
}