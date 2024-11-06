package ro.ase.grupa1095;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;

public class BiletAdapter extends ArrayAdapter<Bilet> {
    private Context context;
    private int layoutId;
    private List<Bilet> bilete;
    private LayoutInflater layoutInflater;

    public BiletAdapter(@NonNull Context context, int layoutId, @NonNull List<Bilet> bilete, LayoutInflater layoutInflater) {
        super(context, layoutId, bilete);
        this.context = context;
        this.layoutId = layoutId;
        this.bilete = bilete;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(layoutId, parent, false);
        Bilet bilet = bilete.get(position);

        TextView tvNume = view.findViewById(R.id.tvNume);
        TextView tvBilete = view.findViewById(R.id.tvNrBilete);
        TextView tvLocatie = view.findViewById(R.id.tvLocatie);
        TextView tvDataConcert = view.findViewById(R.id.tvDataConcert);
        TextView tvMetodaPlata = view.findViewById(R.id.tvMetodaPlata);
        TextView tvCategorie = view.findViewById(R.id.tvCategorie);

        tvNume.setText(bilet.getNume());
        tvBilete.setText(String.valueOf(bilet.getNrBilete()));
        tvLocatie.setText(bilet.getLocatie());
        tvDataConcert.setText(new SimpleDateFormat("dd.MM.yyyy").format(bilet.getDataConcert()));
        tvMetodaPlata.setText(String.valueOf(bilet.getMetodaPlata()));
        tvCategorie.setText(String.valueOf(bilet.getCategorie()));

        if (bilet.getNrBilete() > 4) {
            tvBilete.setTextColor(Color.MAGENTA);
        }

        return view;
    }
}
