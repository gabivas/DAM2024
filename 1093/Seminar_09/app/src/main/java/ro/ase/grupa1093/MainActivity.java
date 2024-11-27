package ro.ase.grupa1093;

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
    private static final String jsonUrl = "https://www.jsonkeeper.com/b/MWBU";
    private ListView lvTarguri;
    private List<TargDeCraciun> targuri = new ArrayList<>();
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
        targuri.add(new TargDeCraciun("Sibiu", 100, true));
        incarcareTarguriDinRetea();
    }

    private void incarcareTarguriDinRetea(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(jsonUrl);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(()->{
                    getJsonFromHttps(json);
                });
            }
        };
        thread.start();
    }

    private void getJsonFromHttps(String json){
        targuri.addAll(TargDeCraciunParser.parsareJson(json));
        ArrayAdapter<TargDeCraciun> adapter = (ArrayAdapter<TargDeCraciun>) lvTarguri.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void initComponente() {
        lvTarguri = findViewById(R.id.lvTarguriDeCraciun);
        ArrayAdapter<TargDeCraciun> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, targuri);
        lvTarguri.setAdapter(adapter);
    }
}