package com.affinity.affinityteam.affinity.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.affinity.affinityteam.affinity.Adapters.CardAdapter;
import com.affinity.affinityteam.affinity.Models.AffinityCard;
import com.affinity.affinityteam.affinity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class AffinityList extends Fragment {

    //1)Solicita n usuarios a firebase.
    //2)Los procesa y se obtienen los posibles match con el current user.
    //3)Se guardan los posibles match en currentUser.matches, directamente en firebase.
    //4)Se muestran los posibles match mediante una lista, en la vista del fragment.
    //5)Cada vez que un posible match es clickeado por el current user, "se solicita match".
        //a)Si el match es possible => matchState = requested
        //b)Si el match es requested => matchState = accepted => se abre la ventanita de chat o un aviso de "tienes match"
        //c)Si el match es accepted => no se deberia mostrar en la lista, se deberia eliminar de currentUser.matches
    //6)Cada vez que el currentUser hace scrollDown "descarga" mas usuarios de firebase para ingresar en la lista.

    RecyclerView recyclerView;
    LinearLayoutManager manager;
    DatabaseReference reference;
    FirebaseUser fuser;
    List<AffinityCard> mUser;
    CardAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_affinity_list, container, false);
        recyclerView = view.findViewById(R.id.rv_list);
        //here goes all the logic!
        mUser = new ArrayList<AffinityCard>();
        //1)Solicita n usuarios a firebase.
        getUserDB();

        adapter = new CardAdapter(getContext(),mUser);
        recyclerView.setAdapter(adapter);

        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        return view;
    }

    private void getUserDB() {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists() && !dataSnapshot.child("Connections").child("Yes").hasChild(dataSnapshot.getKey())  && !dataSnapshot.getKey().equals(fuser.getUid())){

                    String imageUrl = "default";
                    String username = dataSnapshot.child("username").getValue().toString();
                    //int valor = function = retorna affinityScore;
                    int background = R.drawable.bg1;
                    int profilePhoto = R.drawable.profile1;

                    if(!dataSnapshot.child("imageURL").getValue().equals("default")){
                        imageUrl = dataSnapshot.child("imageURL").getValue().toString();
                    }

                    AffinityCard card = new AffinityCard(dataSnapshot.getKey(),background,username,profilePhoto,100);
                    mUser.add(card);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
