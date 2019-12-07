package gestion.informacion.proyectoConFirebase;

public class Permiso {
        String rolName, pantalla;
        boolean acceso, insertar, modificar, borrar;

        public Permiso (){
        }

        public Permiso (String rolName, String pantalla, boolean acceso, boolean insertar, boolean modificar, boolean borrar){
            this.rolName = rolName;
            this.pantalla = pantalla;
            this.acceso = acceso;
            this.insertar = insertar;
            this.modificar = modificar;
            this.borrar = borrar;
        }

        public String getRolName() {
            return rolName;
        }

        public void setRolName(String rolName) {
            this.rolName = rolName;
        }

        public String getPantalla() {
            return pantalla;
        }

        public void setPantalla(String pantalla) {
            this.pantalla = pantalla;
        }

        public boolean isAcceso() {
            return acceso;
        }

        public void setAcceso(boolean acceso) {
            this.acceso = acceso;
        }

        public boolean isInsertar() {
            return insertar;
        }

        public void setInsertar(boolean insertar) {
            this.insertar = insertar;
        }

        public boolean isModificar() {
            return modificar;
        }

        public void setModificar(boolean modificar) {
            this.modificar = modificar;
        }

        public boolean isBorrar() {
            return borrar;
        }

        public void setBorrar(boolean borrar) {
            this.borrar = borrar;
        }
}
