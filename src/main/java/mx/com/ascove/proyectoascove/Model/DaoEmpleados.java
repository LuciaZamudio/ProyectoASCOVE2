package mx.com.ascove.proyectoascove.Model;

import mx.com.ascove.proyectoascove.Model.Usuario.BeanUsuario;
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
import java.sql.*;


public class DaoEmpleados {

    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;


    final private Logger CONSOLE = LoggerFactory.getLogger(DaoEmpleados.class);

    public List<BeanEmpleados> findAll() {
        List<BeanEmpleados> listUsers = new ArrayList<>();

        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call findEmpls}");
            rs = cstm.executeQuery();

            while (rs.next()){
                BeanUsuario user =new BeanUsuario();
                BeanEmpleados emp = new BeanEmpleados();

                emp.setNombreEmpleados(rs.getString("Nombre"));
                emp.setaPaterno(rs.getString("ApellidoPaterno"));
                emp.setaPaterno(rs.getString("ApellidoMaterno"));
                emp.setCalle(rs.getString("Calle"));
                emp.setColonia(rs.getString("Colonia"));
                emp.setMunicipio(rs.getInt("Municipio"));
                listUsers.add(emp);
            }
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return listUsers;
    }

    public boolean create (BeanEmpleados admin){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call registerEmpls(?,?,?,?,?,?,?)}");

            cstm.setString(1,admin.getNombreEmpleados());
            cstm.setString(2,admin.getaPaterno());
            cstm.setString(3,admin.getaMaterno());
            cstm.setString(4,admin.getCalle());
            cstm.setString(5,admin.getColonia());
            cstm.setInt(6,admin.getMunicipio());
            cstm.setInt(7, admin.getIdEmpleado());
            cstm.execute();

            flag = true;

        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;


    }
//Sirve para probar el procedimiento

 /*   public static void main(String[] args) {
        BeanEmpleados mp = new BeanEmpleados();
        DaoEmpleados de = new DaoEmpleados();

        boolean registre = false;
        mp.setIdEmpleado(1);
        mp.setNombreEmpleados("Julio");
        mp.setaPaterno("Gutierrez");
        mp.setaMaterno("Rojas");
        mp.setCalle("Flores");
        mp.setColonia("titg");
        mp.setMunicipio(2);

        registre = de.create(mp);
        if(registre = true){
            System.out.println("Los usuarios se han registrado correctamente");
        }else {
            System.out.println("ha ocurrido un error");
        }
        System.out.println("========================================");
        List<BeanEmpleados> listUsers = new ArrayList<>();
        listUsers = de.findAll();

        for (int i = 0; i < listUsers.size(); i++){
            System.out.println(listUsers.get(i).getNombreEmpleados());
        }
    }*/

}
