package ro.ase.grupa109;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etDenumire;
    private EditText etPret;
    private Button btnGolireDate;
    private FloatingActionButton fabSave;
    private FloatingActionButton fabDelete;
    private ListView lvProduse;
    private List<Produs> produse = new ArrayList<>();
    private FirebaseService firebaseService;
    private int indexProdusSelectat = -1;


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
        firebaseService = FirebaseService.getInstance();
        firebaseService.addProduseListener(dataChangeCallback());
    }

    private Callback<List<Produs>> dataChangeCallback() {
        return rezultat -> {
            produse.clear();
            produse.addAll(rezultat);
            ArrayAdapter<Produs> adapter = (ArrayAdapter<Produs>) lvProduse.getAdapter();
            adapter.notifyDataSetChanged();
            golireText();
        };
    }

    private void initComponente() {
        etDenumire = findViewById(R.id.etDenumire);
        etPret = findViewById(R.id.etPret);
        btnGolireDate = findViewById(R.id.btnGolireDate);
        fabSave = findViewById(R.id.fabSave);
        fabDelete = findViewById(R.id.fabDelete);
        lvProduse = findViewById(R.id.lvProduse);
        ArrayAdapter<Produs> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, produse);
        lvProduse.setAdapter(adapter);
        btnGolireDate.setOnClickListener(golireDateEventListener());
        fabSave.setOnClickListener(saveEventListener());
        fabDelete.setOnClickListener(deleteEventListener());
        lvProduse.setOnItemClickListener(produsSelectatEventListener());
    }

    private AdapterView.OnItemClickListener produsSelectatEventListener() {
        return (adapterView, view, position, param) -> {
            indexProdusSelectat = position;
            Produs produs = produse.get(position);
            etDenumire.setText(produs.getDenumire());
            etPret.setText(String.valueOf(produs.getPret()));
        };
    }

    private View.OnClickListener deleteEventListener() {
        return view -> {
            if (indexProdusSelectat != -1) {
                firebaseService.delete(produse.get(indexProdusSelectat));
            }
        };
    }

    private View.OnClickListener saveEventListener() {
        return view -> {
            if (validare()) {
                if (indexProdusSelectat == -1) {
                    Produs produs = actualizareProdusFromUI(null);
                    firebaseService.insert(produs);
                } else {
                    Produs produs = actualizareProdusFromUI(produse.get(indexProdusSelectat).getId());
                    firebaseService.update(produs);
                }
            }

        };
    }

    private Produs actualizareProdusFromUI(String id) {
        Produs produs = new Produs();
        produs.setId(id);
        produs.setDenumire(etDenumire.getText().toString());
        produs.setPret(Float.valueOf(etPret.getText().toString()));
        return produs;
    }

    private boolean validare() {
        if (etDenumire.getText() == null || etDenumire.getText().toString().isEmpty() || etPret.getText() == null || etPret.getText().toString().isEmpty()) {
            Toast.makeText(this, "Validarea nu a trecut", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private View.OnClickListener golireDateEventListener() {
        return view -> golireText();
    }

    private void golireText() {
        etDenumire.setText(null);
        etPret.setText(null);
        indexProdusSelectat = -1;
    }
}