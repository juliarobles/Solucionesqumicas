package gestion.informacion.proyectoConFirebase;

public class Solucion {
    private int ID;
    private String Solucion;
    private String Uso;

    public Solucion() {
    }

    public Solucion(int ID, String solucion, String uso) {
        this.ID = ID;
        Solucion = solucion;
        Uso = uso;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSolucion() {
        return Solucion;
    }

    public void setSolucion(String solucion) {
        Solucion = solucion;
    }

    public String getUso() {
        return Uso;
    }

    public void setUso(String uso) {
        Uso = uso;
    }
}
