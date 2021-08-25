package mx.com.ascove.proyectoascove.Model.Usuario;

import mx.com.ascove.proyectoascove.Model.Roles.BeanRoles;

public class BeanUsuario {
    private int idUsuario;
    private String correo;
    private String contraseña;
    private BeanRoles idRoles;

    public BeanUsuario() {
    }

    public BeanUsuario(int idUsuario, String correo, String contraseña, BeanRoles idRoles) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.idRoles = idRoles;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public BeanRoles getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(BeanRoles idRoles) {
        this.idRoles = idRoles;
    }
}
