package gestion.informacion.proyectoConFirebase;

public class Usuario {
    private String nif;
    private String password;
    private String rolName;

    public Usuario() {
    }

    public Usuario(String nif, String password, String rolName) {
        this.nif = nif;
        this.password = password;
        this.rolName = rolName;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }
}
