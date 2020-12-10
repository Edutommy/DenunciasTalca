package com.villalobos.appdenunciasproyecto.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.villalobos.appdenunciasproyecto.R;
import com.villalobos.appdenunciasproyecto.model.Denuncias;

public class DenunciarFragment extends Fragment {

    EditText txt_titulo, txt_direccion;
    Button boton;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_denunciar, container, false);

        txt_titulo = view.findViewById(R.id.denunciar_txt_titulo);
        txt_direccion = view.findViewById(R.id.denunciar_txt_direccion);
        boton = view.findViewById(R.id.denunciar_btn_boton);

        auth = FirebaseAuth.getInstance();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = txt_titulo.getText().toString();
                String direccion = txt_direccion.getText().toString();
                String uid = auth.getCurrentUser().getUid();

                if (titulo.isEmpty() || direccion.isEmpty()){
                    //toast
                    Toast.makeText(getActivity(), "Faltan datos", Toast.LENGTH_LONG).show();
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("denuncias").child(uid);

                    Denuncias denuncias = new Denuncias();
                    denuncias.setTitulo(titulo);
                    denuncias.setDireccion(direccion);
                    myRef.push().setValue(denuncias);
                    Toast.makeText(getActivity(), "Denuncia creada", Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }
}