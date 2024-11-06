package ro.ase.grupa1094;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
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
    private int pozitieFoodInLista;


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

        lvFoodList.setOnItemClickListener((adapterView, view, position, l) -> {
            pozitieFoodInLista = position;
            Intent intent = new Intent(getApplicationContext(), AddFoodDeliveryActivity.class);
            intent.putExtra("edit", foodList.get(pozitieFoodInLista));
            launcher.launch(intent);
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getData().hasExtra("foodForIntent")) {
                Intent intent = result.getData();
                Food food = (Food) intent.getSerializableExtra("foodForIntent");
                //Toast.makeText(this, food.toString(), Toast.LENGTH_SHORT).show();
                if (food != null) {
                    foodList.add(food);
                }
                //ArrayAdapter<Food> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, foodList);
                FoodAdapter adapter = new FoodAdapter(this, R.layout.view_food, foodList, getLayoutInflater());
                lvFoodList.setAdapter(adapter);
            } else if (result.getData().hasExtra("edit")) {
                Intent intent = result.getData();
                Food food = (Food) intent.getSerializableExtra("edit");
                //Toast.makeText(this, food.toString(), Toast.LENGTH_SHORT).show();
                if (food != null) {
                    Food editedFood = foodList.get(pozitieFoodInLista);
                    editedFood.setNume(food.getNume());
                    editedFood.setAdresa(food.getAdresa());
                    editedFood.setCantitate(food.getCantitate());
                    editedFood.setPret(food.getPret());
                    editedFood.setDataLivrare(food.getDataLivrare());
                    editedFood.setProduse(food.getProduse());
                    editedFood.setValuta(food.getValuta());

                    FoodAdapter adapter = (FoodAdapter) lvFoodList.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }

        });

        SharedPreferences sharedPreferences = getSharedPreferences("local", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("token", "token1234");
        editor.apply();

        String token = sharedPreferences.getString("token", "Default");
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();

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