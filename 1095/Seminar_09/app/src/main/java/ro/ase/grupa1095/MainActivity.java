package ro.ase.grupa1095;

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
    private static final String jsonURL = "https://www.jsonkeeper.com/b/4H53";
    private ListView lvCamere;
    private List<Camera> camere = new ArrayList<>();

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
        initializareComponente();
        camere.add(new Camera(21.56f, "bucatarie", "alb"));
        incarcareCamereDinRetea();
    }

    private void incarcareCamereDinRetea(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(jsonURL);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(()->{
                    getCamereJson(json);
                });
            }
        };
        thread.start();
    }

    private void getCamereJson(String json) {
        camere.addAll(CamaraParser.parsareJson(json));
        ArrayAdapter<Camera> adapter = (ArrayAdapter<Camera>) lvCamere.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void initializareComponente() {
        lvCamere = findViewById(R.id.lvCamere);
        ArrayAdapter<Camera> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, camere);
        lvCamere.setAdapter(adapter);
    }
}