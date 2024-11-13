package ro.ase.grupa1093;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        HorizontalScrollView scrollView = new HorizontalScrollView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ListView listView = new ListView(this);
        TextView textView = new TextView(this);
        textView.setText("Lista shaormas:");

        ShaormaDB dbInstance = ShaormaDB.getInstance(getApplicationContext());

        Shaorma shaorma = new Shaorma(600, 30, 500);
        dbInstance.getShaormaDAO().insertShaorma(shaorma);
        List<Shaorma> shaormas = dbInstance.getShaormaDAO().getAllShaormas();

        ArrayAdapter<Shaorma> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, shaormas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            Shaorma deletedShaorma = shaormas.get(position);
            shaormas.remove(deletedShaorma);
            dbInstance.getShaormaDAO().deleteShaorma(deletedShaorma);
            adapter.notifyDataSetChanged();
        });

        linearLayout.addView(textView);
        linearLayout.addView(listView);
        scrollView.addView(linearLayout);

        setContentView(scrollView);
    }
}