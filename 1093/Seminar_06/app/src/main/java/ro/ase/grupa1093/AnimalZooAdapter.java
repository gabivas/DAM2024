package ro.ase.grupa1093;

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

public class AnimalZooAdapter extends ArrayAdapter<AnimalZoo> {
    private Context context;
    private List<AnimalZoo> animalZooList;
    private int layoutId;
    private LayoutInflater inflater;

    public AnimalZooAdapter(@NonNull Context context, int resource, @NonNull List<AnimalZoo> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.layoutId = resource;
        this.animalZooList = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(layoutId, parent, false);
        AnimalZoo animalZoo = animalZooList.get(position);
        TextView tvSpecie = view.findViewById(R.id.tvSpecie);
        TextView tvGreutate = view.findViewById(R.id.tvGreutate);
        TextView tvVarsta = view.findViewById(R.id.tvVarsta);
        TextView tvTaraOrigine = view.findViewById(R.id.tvTaraOrigine);
        TextView tvDataNastere = view.findViewById(R.id.tvDataNastere);
        TextView tvStilAlimentar = view.findViewById(R.id.tvStilAlimentar);
        TextView tvSex = view.findViewById(R.id.tvSex);

        tvSpecie.setText(animalZoo.getSpecie());
        tvGreutate.setText(String.valueOf(animalZoo.getGreutate()));
        tvVarsta.setText(String.valueOf(animalZoo.getVarsta()));
        tvTaraOrigine.setText(animalZoo.getTaraOrigine());
        tvDataNastere.setText(new SimpleDateFormat("dd/MM/yyyy").format(animalZoo.getDataNastere()));
        tvStilAlimentar.setText(String.valueOf(animalZoo.getStilAlimentar()));
        tvSex.setText(String.valueOf(animalZoo.getSex()));

        if (animalZoo.getGreutate() > 3) {
            tvGreutate.setTextColor(Color.GRAY);
        }
        return view;
    }
}
