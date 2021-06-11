package com.affinity.affinityteam.affinity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.affinity.affinityteam.affinity.Models.Logro;
import com.affinity.affinityteam.affinity.Models.Topic;
import com.affinity.affinityteam.affinity.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    MaterialEditText username, email,password;
    Button btn_register;
    FirebaseAuth auth;
    DatabaseReference reference;
    User u;
     FakeGenerator fakeGen = new FakeGenerator();
    String txt_username,txt_email,txt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        btn_register = findViewById(R.id.btn_register);

        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d("Hey","Something thingy"+firebaseAuth.getUid());

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_username = username.getText().toString();
                txt_email = email.getText().toString();
                txt_password = password.getText().toString();

                Toast.makeText(RegisterActivity.this, "Apretaste la cosita", Toast.LENGTH_SHORT).show();

                if(TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(RegisterActivity.this,"Fill up all requirements",Toast.LENGTH_SHORT);
                } else if(txt_password.length() < 6){
                    Toast.makeText(RegisterActivity.this,"Your Password must be at least 6 digits",Toast.LENGTH_SHORT);


                } else {
                    Toast.makeText(RegisterActivity.this, "Tratando de registrar", Toast.LENGTH_SHORT).show();
                    //creatingFakeUsers(5);

                    register(txt_username,txt_email,txt_password);
                }

            }
        });
    }
    public void register(final String username, String email, String password)
    {
        auth = FirebaseAuth.getInstance();
        Toast.makeText(RegisterActivity.this, "Fuser:"+auth.getUid(), Toast.LENGTH_SHORT).show();

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            Toast.makeText(RegisterActivity.this, "De oro!", Toast.LENGTH_SHORT).show();

                            creatingFakeUsers(5);

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
                            //creatingFakeUsers(300);
                            //List<Logro> logros = new ArrayList<Logro>();
                            List<Topic> topics = new ArrayList<Topic>();

                            topics = fakeGen.topicsGenerator();
                            //logros.add(new Logro(-1,"nulo",-1));
                            u = new User();

                            u.setNombre(username);
                            u.setUsername(username);
                            u.setTopics(topics); //fakeGen
                            //u.setLogros(logros); //null
                            u.setCarrera("Ing.Informatica");
                            u.setDescripcion("Me llamo Clotilde y me gustan las tildes");
                            u.setUniversidadOrigen("U. de Valparaiso");
                            u.setUniversidadDestino("Roma tre");
                            fakeGen.setRandomLocation(u);
                            u.setImageURL("default");
                            u.setRadioBusqueda(new Random().nextDouble() *30);
                            u.setStatus("offline");
                            u.setEdad(22);
                            u.setPaisOrigen("Chile");
                            u.setPaisDestino("Italia");
                            u.setUser_type(0);
                            u.setCiudadActual("Roma");
                            u.setUserState(true);



                            Toast.makeText(RegisterActivity.this, "About to work!!", Toast.LENGTH_SHORT).show();

                            reference.setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){


                                        Toast.makeText(RegisterActivity.this, "It works dude!!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();


                                    }
                                }
                            });
                            Toast.makeText(RegisterActivity.this, "Todo listo cumpita!", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(RegisterActivity.this,"You cant register with this email or password",Toast.LENGTH_SHORT);
                        }
                    }
                });

    }


    public void creatingFakeUsers(int number){
        reference = FirebaseDatabase.getInstance().getReference("Users");
        List<User> fake_user_list = new ArrayList<User>();
        fake_user_list = fakeGen.listUserGenerator(number);

        for(User user : fake_user_list){

            String mGroupId = reference.push().getKey();
            Log.d("refp",mGroupId);

            reference.child(mGroupId).setValue(user);

        }
        Toast.makeText(RegisterActivity.this, "300 THIS IS SPARTAAA!", Toast.LENGTH_SHORT).show();
    }
}
