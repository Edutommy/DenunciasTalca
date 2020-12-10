package com.villalobos.appdenunciasproyecto.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.villalobos.appdenunciasproyecto.R;
import com.villalobos.appdenunciasproyecto.model.Denuncias;

import java.util.ArrayList;
import java.util.List;

public class DenunciasFragment extends Fragment {

    TextView txt;
    FirebaseAuth auth;
    List<Denuncias> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_denuncias, container, false);

        txt = view.findViewById(R.id.denuncias_txt_denuncias);
        auth = FirebaseAuth.getInstance();

        list = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("denuncias");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    list.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Denuncias denuncias = ds.getValue(Denuncias.class);
                        denuncias.setId(ds.getKey());
                        list.add(denuncias);
                    }

                    String res = "";
                    for (Denuncias d : list) {
                        res += d.getTitulo() + " || " + d.getDireccion() + "\n";
                    }
                    txt.setText(res);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}