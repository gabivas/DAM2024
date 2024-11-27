package ro.ase.grupa1094;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //private static final String urlMasini = "https://www.jsonkeeper.com/b/PPTN";
    private static final String urlMasini = "https://138.68.47.227/b/PPTN";
    private ListView lvMasini;
    private List<Masina> masini = new ArrayList<>();

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
        initComponente();
        masini.add(new Masina("serie123", 1500, "Renault"));
        masini.add(new Masina("serie456", 2000, "Ford"));
        incarcareMasiniDinRetea();
    }

    private void incarcareMasiniDinRetea() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                HttpsManager httpsManager = new HttpsManager(urlMasini);
                String rezultat = httpsManager.procesare();
                new Handler(getMainLooper()).post(() -> {
                    preluareMasiniDinJson(rezultat);
                });
            }
        };
        thread.start();
    }

    private void preluareMasiniDinJson(String json) {
        masini.addAll(MasinaParser.parsareJson(json));
        ArrayAdapter<Masina> adapter = (ArrayAdapter<Masina>) lvMasini.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void initComponente() {
        lvMasini = findViewById(R.id.lvMasini);
        ArrayAdapter<Masina> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, masini);
        lvMasini.setAdapter(adapter);
    }
}