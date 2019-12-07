package gestion.informacion.proyectoConFirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button login, cancel;
    private EditText pass, user;
    private Toast toast1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        login = findViewById(R.id.login);
        cancel = findViewById(R.id.cancelar);
        pass = findViewById(R.id.password);
        user = findViewById(R.id.username);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if((pass.getText().length() > 0) && (user.getText().length() > 0)){
                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Usuario u = dataSnapshot.child("tUsuario").child(user.getText().toString()).getValue(Usuario.class);
                            if(u != null && u.getPassword().equals(pass.getText().toString())){
                                Rol r = dataSnapshot.child("tRol").child(u.getRolName()).getValue(Rol.class);
                                toast1 = Toast.makeText(getApplicationContext(), "Entra correctamente", Toast.LENGTH_SHORT);
                                toast1.show();
                                Intent intent = new Intent (v.getContext(), MuestraActivity.class);
                                startActivityForResult(intent, 0);
                            } else {
                                toast1 = Toast.makeText(getApplicationContext(), "Contraseña o usuario incorrecto", Toast.LENGTH_SHORT);
                                toast1.show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else {
                    toast1 = Toast.makeText(getApplicationContext(), "Ambos campos deben estar rellenos", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });

    }





}
