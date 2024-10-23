package ro.ase.grupa1094;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabOpen;
    ListView lvFoodList;
    List<Food> foodList = new ArrayList<>();
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

        lvFoodList = findViewById(R.id.lvFoodList);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();
                Food food = (Food) intent.getSerializableExtra("foodForIntent");
                Toast.makeText(this, food.toString(), Toast.LENGTH_SHORT).show();
            }

        });

        fabOpen = findViewById(R.id.fabOpen);
        fabOpen.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddFoodDeliveryActivity.class);
            //startActivity(intent);
            launcher.launch(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.idOptiune1) {
            Toast.makeText(this, "OPTIUNE 1", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}