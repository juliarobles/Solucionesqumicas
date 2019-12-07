package gestion.informacion.proyectoConFirebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BD {

    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    public List<Muestra> listaMuestras(){
        final List<Muestra> muestras = new ArrayList<>();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot datosmuestras = dataSnapshot.child("tMuestra");
                Iterable<DataSnapshot> listamuestras = datosmuestras.getChildren();

                for (DataSnapshot m : listamuestras) {
                    muestras.add(m.getValue(Muestra.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return muestras;
    }

    public List<Solucion> listaSoluciones(){
        final List<Solucion> soluciones = new ArrayList<>();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot datossol = dataSnapshot.child("tSolucion");
                Iterable<DataSnapshot> listasol = datossol.getChildren();

                for (DataSnapshot m : listasol) {
                    soluciones.add(m.getValue(Solucion.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return soluciones;
    }

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
