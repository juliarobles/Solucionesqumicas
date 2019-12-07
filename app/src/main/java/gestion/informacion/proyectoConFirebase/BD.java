package gestion.informacion.proyectoConFirebase;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BD {

    private static DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    /*
    private static List<Muestra> todasMuestras;
    private static List<Solucion> todasSoluciones;

    public BD() {
        listaMuestras();
        listaSoluciones();
    }

    public static void listaMuestras(){
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                todasMuestras = new ArrayList<>();
                DataSnapshot datosmuestras = dataSnapshot.child("tMuestra");
                Iterable<DataSnapshot> listamuestras = datosmuestras.getChildren();

                for (DataSnapshot m : listamuestras) {
                    todasMuestras .add(m.getValue(Muestra.class));
                    Log.i("muestra", m.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void listaSoluciones(){
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                todasSoluciones = new ArrayList<>();
                DataSnapshot datossol = dataSnapshot.child("tSolucion");
                Iterable<DataSnapshot> listasol = datossol.getChildren();

                for (DataSnapshot m : listasol) {
                    Log.i("jeje", m.getValue().toString());
                    todasSoluciones.add(m.getValue(Solucion.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public List<Solucion> getSoluciones(){
        while(todasSoluciones == null){
            listaSoluciones();
        }
        Log.i("lista", todasSoluciones.toString());
        return todasSoluciones;
    }

    public List<Muestra> getMuestras(){
        while(todasMuestras == null){
            listaMuestras();
        }
        Log.i("lista", todasSoluciones.toString());
        return todasMuestras;
    }
*/
    public Solucion sacarSolucionPorId(final int idSol){
        final Solucion[] res = {null};
        db.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                res[0] = dataSnapshot.child("tSolucion").child(Integer.toString(idSol)).getValue(Solucion.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return res[0];
    }


    public void actualizarCultivoMuestra(int id, String cultivo){
        db.child("tMuestra").child(Integer.toString(id)).child("Cultivo").setValue(cultivo);
    }

    public void actualizarIDMuestra(int id, int idNuevo){
        db.child("tMuestra").child(Integer.toString(id)).child("ID").setValue(idNuevo);
    }

    public void actualizarNIFPacienteMuestra(int id, String nif){
        db.child("tMuestra").child(Integer.toString(id)).child("NIF_PACIENTE").setValue(nif);
    }

    public void actualizarSolucionMuestra(int id, int idSol){
        db.child("tMuestra").child(Integer.toString(id)).child("Solucion").setValue(idSol);
    }

    public void borrarMuestra(int id){
        db.child("tMuestra").child(Integer.toString(id)).removeValue();
    }

    public void addMuestra(int id, String cultivo, String nifPaciente, int solucion){
        db.child("tMuestra").child(Integer.toString(id)).setValue(new Muestra(id, nifPaciente, cultivo, solucion));
    }

}
