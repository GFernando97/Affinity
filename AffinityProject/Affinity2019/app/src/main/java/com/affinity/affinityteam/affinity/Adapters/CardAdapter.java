package com.affinity.affinityteam.affinity.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.affinity.affinityteam.affinity.Models.AffinityCard;
import com.affinity.affinityteam.affinity.Models.User;
import com.affinity.affinityteam.affinity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.myViewHolder> {

    Context mContext;
    List<AffinityCard> mData;
    FirebaseUser fuser;
    DatabaseReference ref;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public CardAdapter(Context mContext, List<AffinityCard> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.affinity_card,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {
        holder.background_img.setImageResource(mData.get(position).getBackground());
        holder.profile_photo.setImageResource(mData.get(position).getProfilePhoto());
        holder.tv_title.setText(mData.get(position).getUsername());
        holder.tv_nbFollowers.setText(mData.get(position).getAffinityScore()+" Points");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fuser = FirebaseAuth.getInstance().getCurrentUser();
                ref = FirebaseDatabase.getInstance().getReference("Users");


                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Comprobar si existe datasnapshot (si hay current user)
                        if(dataSnapshot.exists()){
                            DatabaseReference refTo;
                            //1)Revisar si en otherUser ya fue aceptado,
                                //Si -> Es un match entre ambos!
                                //No -> se agrega a current yeps
                            if(dataSnapshot.child(mData.get(position).getOtherUserId()).child("Connections").child("Yes").hasChild(fuser.getUid())){
                                //Obtener referencia de current user -> match
                                refTo = dataSnapshot.child(fuser.getUid()).child("Connections").child("Match").getRef();
                                //Agregar match a current user -> value es id del otro user
                                refTo.child(mData.get(position).getOtherUserId()).setValue(true);
                                //Agregando el valor del match a otherUser
                                refTo.child(mData.get(position).getOtherUserId()).child("AffinityScore").setValue(mData.get(position).getAffinityScore());


                                //Obtener referencia de otherUser -> match
                                refTo = dataSnapshot.child(mData.get(position).getOtherUserId()).child("Connections").child("Match").getRef();
                                //Agregar match a otherUser -> value es id del currentUser
                                refTo.child(fuser.getUid()).setValue(true);
                                //Agregando el valor del match a currentUser
                                refTo.child(fuser.getUid()).child("AffinityScore").setValue(mData.get(position).getAffinityScore());


                                //Voila! there is a match!!!
                                Toast.makeText(mContext, "Voila! there is a match, otherUserid:"+mData.get(position).getOtherUserId(), Toast.LENGTH_SHORT).show();

                            } else {

                                //Obtener la referencia de currentUser -> connections -> yes
                                refTo = dataSnapshot.child(fuser.getUid()).child("Connections").child("Yes").getRef();
                                //Agregar other user id a currentUser -> connections -> yes
                                refTo.child(mData.get(position).getOtherUserId()).setValue(true);

                                Toast.makeText(mContext, "Se agrega el user:"+mData.get(position).getOtherUserId()+"currentUser  yes!!!!", Toast.LENGTH_SHORT).show();
                            }

                            //Luego de ser tocados, se elimina de la lista, y se actualiza la vista
                            mData.remove(position);
                            notifyDataSetChanged();


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_photo,background_img;
        TextView tv_title, tv_nbFollowers;

        public myViewHolder(View itemView){
            super(itemView);
            profile_photo = itemView.findViewById(R.id.profile_img);
            background_img = itemView.findViewById(R.id.card_background);
            tv_title = itemView.findViewById(R.id.card_title);
            tv_nbFollowers = itemView.findViewById(R.id.card_nb_followers);

        }
    }
}
