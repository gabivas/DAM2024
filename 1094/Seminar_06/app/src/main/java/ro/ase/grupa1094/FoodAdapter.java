package ro.ase.grupa1094;

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

public class FoodAdapter extends ArrayAdapter<Food> {
    private Context context;
    private int layoutId;
    private List<Food> foodList;
    private LayoutInflater inflater;

    public FoodAdapter(@NonNull Context context, int resource, @NonNull List<Food> foodList, LayoutInflater inflater) {
        super(context, resource, foodList);
        this.context = context;
        this.layoutId = resource;
        this.foodList = foodList;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(layoutId, parent, false);
        Food food = foodList.get(position);

        TextView tvNume = view.findViewById(R.id.tvNume);
        TextView tvPret = view.findViewById(R.id.tvPret);
        TextView tvCantitate = view.findViewById(R.id.tvCantitate);
        TextView tvAdresa = view.findViewById(R.id.tvAdresa);
        TextView tvDataLivrare = view.findViewById(R.id.tvDataLivrare);
        TextView tvProduse = view.findViewById(R.id.tvProduse);
        TextView tvValuta = view.findViewById(R.id.tvValuta);

        tvNume.setText(food.getNume());
        tvPret.setText(String.valueOf(food.getPret()));
        tvCantitate.setText(String.valueOf(food.getCantitate()));
        tvAdresa.setText(food.getAdresa());
        tvDataLivrare.setText(new SimpleDateFormat("dd/MM/yyyy").format(food.getDataLivrare()));
        tvProduse.setText(String.valueOf(food.getProduse()));
        tvValuta.setText(String.valueOf(food.getValuta()));

        if (food.getPret() > 100) {
            tvPret.setTextColor(Color.GREEN);
        }
        return view;
    }
}
