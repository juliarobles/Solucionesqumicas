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

    public void actualizarCultivoMuestra(int id, String cultivo){
        db.child("tMuestra").child(Integer.toString(id)).child("cultivo").setValue(cultivo);
    }

    public void actualizarIDMuestra(final int id, final int idNuevo){
        db.child("tMuestra").child(Integer.toString(id)).child("id").setValue(idNuevo);
        db.child("tMuestra").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Muestra m = dataSnapshot.child(Integer.toString(id)).getValue(Muestra.class);
                Log.i("sad", m.toString());
                db.child("tMuestra").child(Integer.toString(id)).removeValue();
                db.child("tMuestra").child(Integer.toString(idNuevo)).setValue(new Muestra(m.getID(), m.getNIF_Paciente(), m.getCultivo(), m.getSolucion()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void actualizarNIFPacienteMuestra(int id, String nif){
        db.child("tMuestra").child(Integer.toString(id)).child("nif_Paciente").setValue(nif);
    }

    public void actualizarSolucionMuestra(int id, int idSol){
        db.child("tMuestra").child(Integer.toString(id)).child("solucion").setValue(idSol);
    }

    public void borrarMuestra(int id){
        db.child("tMuestra").child(Integer.toString(id)).removeValue();
    }

    public void addMuestra(final int ID, final String Cultivo, final String NIF_Paciente, final int Solucion){
        db.child("tMuestra").child(Integer.toString(ID)).setValue(new Muestra(ID, NIF_Paciente, Cultivo, Solucion));
    }
}
