package gestion.informacion.proyectoConFirebase;

public class Rol {
    private String rolName;
    private String rolDes;
    private long admin;

    public Rol() {
    }

    public Rol(String rolName, String rolDes, long admin) {
        this.rolName = rolName;
        this.rolDes = rolDes;
        this.admin = admin;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public String getRolDes() {
        return rolDes;
    }

    public void setRolDes(String rolDes) {
        this.rolDes = rolDes;
    }

    public long getAdmin() {
        return admin;
    }

    public void setAdmin(long admin) {
        this.admin = admin;
    }
}
