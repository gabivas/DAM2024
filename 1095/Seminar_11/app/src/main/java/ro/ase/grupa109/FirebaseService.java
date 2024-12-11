package ro.ase.grupa109;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService {
    private final DatabaseReference reference;
    private static FirebaseService firebaseService;

    private FirebaseService() {
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseService getInstance() {
        if (firebaseService == null) {
            synchronized (FirebaseService.class) {
                if (firebaseService == null) {
                    firebaseService = new FirebaseService();
                }
            }
        }
        return firebaseService;
    }

    public void insert(Produs produs) {
        if (produs == null || produs.getId() != null) {
            return;
        }
        String id = reference.push().getKey();
        produs.setId(id);
        reference.child(produs.getId()).setValue(produs);
    }

    public void update(Produs produs) {
        if (produs == null || produs.getId() == null) {
            return;
        }
        reference.child(produs.getId()).setValue(produs);
    }

    public void delete(Produs produs) {
        if (produs == null || produs.getId() == null) {
            return;
        }
        reference.child(produs.getId()).removeValue();
    }

    public void addProduseListener(Callback<List<Produs>> callback) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Produs> produse = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Produs produs = data.getValue(Produs.class);
                    if (produs != null) {
                        produse.add(produs);
                    }
                }
                callback.runOnUI(produse);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("firebase", "Produsul nu este disponibil!");
            }
        });
    }
}
