package gestion.informacion.proyectoConFirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MuestraActivity extends AppCompatActivity {

    private ListView muestras, soluciones;
    private DatabaseReference db;
    private List<Muestra> todasMuestras = new ArrayList<>();
    private List<Solucion> todasSoluciones = new ArrayList<>();
    private ArrayAdapter adaptador1, adaptador2;
    private static EditText id, nif, cultivo;
    private Muestra muestraActiva;
    private Solucion solucionSeleccionada;
    private Button insertar, actualizar, borrar, salir;
    private Toast toast1;

    //TODO las clases permisos sacadas de la base de datos
    private Permiso permisoSoluciones,permisoFormulas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra);

        final String rolName = getIntent().getStringExtra("rolNameIntent");

        muestras = findViewById(R.id.listaMuestra);
        soluciones = findViewById(R.id.listaSoluciones);
        id = findViewById(R.id.id);
        nif = findViewById(R.id.nif);
        cultivo = findViewById(R.id.cultivo);

        //botones
        insertar = findViewById(R.id.insertar);
        actualizar = findViewById(R.id.actualizar);
        borrar = findViewById(R.id.borrar);
        salir = findViewById(R.id.salir);

        adaptador1 = new ArrayAdapter<Muestra>(this, android.R.layout.simple_list_item_1, todasMuestras);
        adaptador2 = new ArrayAdapter<Solucion>(this, android.R.layout.simple_list_item_1, todasSoluciones);

        muestras.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        soluciones.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        muestras.setAdapter(adaptador1);
        soluciones.setAdapter(adaptador2);

        db = FirebaseDatabase.getInstance().getReference();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                permisoSoluciones = dataSnapshot.child("tPermiso").child(rolName+"soluciones").getValue(Permiso.class);
                permisoFormulas = dataSnapshot.child("tPermiso").child(rolName+"formulas").getValue(Permiso.class);

                DataSnapshot datosmuestras = dataSnapshot.child("tMuestra");
                Iterable<DataSnapshot> listamuestras = datosmuestras.getChildren();

                for (DataSnapshot m : listamuestras) {
                    todasMuestras.add(m.getValue(Muestra.class));
                    //Log.i("muestra", m.getValue().toString());
                }

                adaptador1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot datossol = dataSnapshot.child("tSolucion");
                Iterable<DataSnapshot> listasol = datossol.getChildren();

                for (DataSnapshot m : listasol) {
                    Log.i("jeje", m.getValue().toString());
                    todasSoluciones.add(m.getValue(Solucion.class));
                }

                adaptador2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        muestras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                muestraActiva = (Muestra)adaptador1.getItem(position);
                actualizarMuestra();
            }
        });

        soluciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                marcarSoluciones(position);
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(muestraActiva != null){
                    BD mibd = new BD();
                    if(!cultivo.getText().equals(muestraActiva.getCultivo())){
                        mibd.actualizarCultivoMuestra(muestraActiva.getID(), cultivo.getText().toString());
                        muestraActiva.setCultivo(cultivo.getText().toString());
                    }
                    if(!nif.getText().equals(muestraActiva.getNIF_Paciente())){
                        mibd.actualizarNIFPacienteMuestra(muestraActiva.getID(), nif.getText().toString());
                        muestraActiva.setNIF_Paciente(nif.getText().toString());
                    }
                    if(solucionSeleccionada != null && muestraActiva.getSolucion() != solucionSeleccionada.getID()){
                        mibd.actualizarSolucionMuestra(muestraActiva.getID(), solucionSeleccionada.getID());
                        muestraActiva.setSolucion(solucionSeleccionada.getID());
                    }

                    if(Integer.parseInt(id.getText().toString()) != muestraActiva.getID()){
                        mibd.actualizarIDMuestra(muestraActiva.getID(), Integer.parseInt(id.getText().toString()));
                        muestraActiva.setID(Integer.parseInt(id.getText().toString()));
                    }

                    adaptador1.notifyDataSetChanged();
                    toast1 = Toast.makeText(getApplicationContext(), "Actualizado correctamente", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(muestraActiva != null){
                    BD mibd = new BD();
                    mibd.borrarMuestra(muestraActiva.getID());
                    todasMuestras.remove(muestraActiva);
                    muestraActiva = null;
                    adaptador1.notifyDataSetChanged();
                    toast1 = Toast.makeText(getApplicationContext(), "Borrado correctamente", Toast.LENGTH_SHORT);
                    toast1.show();
                }

            }
        });

        insertar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                BD mibd = new BD();
                    mibd.addMuestra(Integer.parseInt(id.getText().toString()), cultivo.getText().toString(), nif.getText().toString(), solucionSeleccionada.getID());
                    Muestra nueva =  new Muestra(Integer.parseInt(id.getText().toString()), nif.getText().toString(), cultivo.getText().toString(), solucionSeleccionada.getID());
                    todasMuestras.add(nueva);
                    muestraActiva = nueva;
                    adaptador1.notifyDataSetChanged();
                    toast1 = Toast.makeText(getApplicationContext(), "Insertado correctamente", Toast.LENGTH_SHORT);
                    toast1.show();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Prueba de que los valores de permiso se sacan bien. Ahora hay que modificar los botones para que hagan lo que deberian hacer.
                toast(String.valueOf(permisoFormulas.insertar)+String.valueOf(permisoSoluciones.modificar));
            }
        });
    }

    private void actualizarMuestra(){
        if(muestraActiva != null){
            id.setText(Integer.toString(muestraActiva.getID()));
            cultivo.setText(muestraActiva.getCultivo());
            nif.setText(muestraActiva.getNIF_Paciente());
            int posSol = buscarIndex(muestraActiva.getSolucion());
            marcarSoluciones(posSol);
            Log.i("pos", Integer.toString(posSol));

        }
    }

    private int buscarIndex(int solucion) {
        int index = -1, i = 0;
        while(index == -1 && i < todasSoluciones.size()){
            if(todasSoluciones.get(i).getID() == solucion){
                index = i;
            } else {
                i++;
            }
        }
        return index;
    }

    private void marcarSoluciones(final int posSol) {
        if (soluciones != null && posSol >= 0) {
            solucionSeleccionada = todasSoluciones.get(posSol);
            soluciones.clearFocus();
            soluciones.requestFocusFromTouch();
            soluciones.post(new Runnable() {
                @Override
                public void run() {
                    soluciones.setItemChecked(posSol, true);
                    soluciones.setSelection(posSol);
                }
            });
        } else {
            solucionSeleccionada = null;
        }
    }

    private void toast(String message)
    {
        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }
}
