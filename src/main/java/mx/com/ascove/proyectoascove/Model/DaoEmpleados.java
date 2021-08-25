package mx.com.ascove.proyectoascove.Model;

import mx.com.ascove.proyectoascove.Service.ConnectionMySQL;
import com.sun.jndi.ldap.Ber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoEmpleados {

    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;

    final private Logger CONSOLE = LoggerFactory.getLogger(DaoEmpleados.class);
    public boolean create (BeanEmpleados admin){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call registerEmpls(?,?,?,?,?,?)}");

            cstm.setString(1,admin.getNombreEmpleados());
            cstm.setString(2,admin.getaPaterno());
            cstm.setString(3,admin.getaMaterno());
            cstm.setString(4,admin.getCalle());
            cstm.setInt(5,admin.getMunicipio());
            cstm.setInt(6, admin.getIdEmpleado());
            cstm.execute();

            flag = true;

        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;


    }

  /*  public static void main(String[] args) {
        BeanEmpleados mp = new BeanEmpleados();
        DaoEmpleados de = new DaoEmpleados();
        boolean registre = false;
        mp.setIdEmpleado(1);
        mp.setNombreEmpleados("Julio");
        mp.setaPaterno("Gutierrez");
        mp.setaMaterno("Rojas");
        mp.setCalle("Flores");
        mp.setColonia("Burgos");
        mp.setMunicipio(2);

        registre = de.create(mp);
        if(registre = true){
            System.out.println("Los usuuarios se han registrado correctamente");
        }else {
            System.out.println("ha ocurrido un error");
        }
    }*/
}
