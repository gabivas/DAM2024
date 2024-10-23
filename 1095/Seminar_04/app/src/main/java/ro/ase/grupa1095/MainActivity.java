package ro.ase.grupa1095;

import android.content.Intent;
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
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();
                Bilet bilet = (Bilet) intent.getSerializableExtra("biletFromIntent");
                Toast.makeText(this, bilet.toString(), Toast.LENGTH_SHORT).show();
                if (bilet != null) {
                    listaBilete.add(bilet);
                    ArrayAdapter<Bilet> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listaBilete);
                    lvListaBilete.setAdapter(adapter);
                }
            }
        });

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