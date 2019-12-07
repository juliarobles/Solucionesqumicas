package gestion.informacion.proyectoConFirebase;

public class Solucion {
    int id;
    String solucion, uso;

    public Solucion (){
    }

    public Solucion (int id, String solucion, String uso){
        this.id = id;
        this.solucion = solucion;
        this.uso = uso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }
}
