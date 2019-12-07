package gestion.informacion.proyectoConFirebase;

public class Muestra {
    private int ID;
    private String NIF_Paciente;
    private String Cultivo;
    private int Solucion;

    public Muestra() {
    }

    public Muestra(int ID, String NIF_Paciente, String Cultivo, int Solucion) {
        this.ID = ID;
        this.Solucion = Solucion;
        this.NIF_Paciente = NIF_Paciente;
        this.Cultivo = Cultivo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getNIF_Paciente() {
        return NIF_Paciente;
    }

    public void setNIF_Paciente(String nIF_Paciente) {
        NIF_Paciente = nIF_Paciente;
    }

    public String getCultivo() {
        return Cultivo;
    }

    public void setCultivo(String cultivo) {
        Cultivo = cultivo;
    }

    public int getSolucion() {
        return Solucion;
    }

    public void setSolucion(int solucion) {
        Solucion = solucion;
    }
}
