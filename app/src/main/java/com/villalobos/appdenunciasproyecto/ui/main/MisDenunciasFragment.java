package com.villalobos.appdenunciasproyecto.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.villalobos.appdenunciasproyecto.R;
import com.villalobos.appdenunciasproyecto.adapter.AdapterDenuncia;
import com.villalobos.appdenunciasproyecto.model.Denuncias;

import java.util.ArrayList;
import java.util.List;

public class MisDenunciasFragment extends Fragment {


    FirebaseAuth auth;
    List<Denuncias> list;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mis_denuncias, container, false);

        recyclerView = view.findViewById(R.id.recycle_denuncia);

        auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        list = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("denuncias").child(uid);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    list.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Denuncias denuncias = ds.getValue(Denuncias.class);
                        denuncias.setId(ds.getKey());
                        list.add(denuncias);
                    }
                    LinearLayoutManager lm = new LinearLayoutManager(getActivity());
                    lm.setOrientation(RecyclerView.VERTICAL);

                    AdapterDenuncia adapterDenuncia = new AdapterDenuncia(getActivity(),R.layout.item_denuncia,list);
                    recyclerView.setLayoutManager(lm);
                    recyclerView.setAdapter(adapterDenuncia);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        return view;
    }
}