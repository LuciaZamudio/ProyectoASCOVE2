package mx.com.ascove.proyectoascove.Controller;

import com.google.gson.Gson;
import mx.com.ascove.proyectoascove.Model.BeanEmpleados;
import mx.com.ascove.proyectoascove.Model.DaoEmpleados;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "ServletAdmin", urlPatterns = {"/readUsers", "/createUser"})

public class ServletAdmin extends HttpServlet {

    private Map map = new HashMap();
    final private Logger CONSOLE = LoggerFactory.getLogger(ServletAdmin.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             //List<BeanEmpleados> listAdmin = new DaoEmpleados().findAll();
             request.setAttribute("listUsers", new DaoEmpleados().findAll());
        request.getRequestDispatcher("/views/admin/Admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        /*BeanUsers beanUsers = new BeanUsers();
        BeanRole beanRole = new BeanRole();*/
        DaoEmpleados daoEmpleados = new DaoEmpleados();
        // DaoUsers daoUsers = new DaoUsers();

        switch (action) {
            case "create":

                int idRol = request.getParameter("idRol") != null ? Integer.parseInt(request.getParameter("idRol")) : 0;
                String nombre = request.getParameter("nombre") != null ? request.getParameter("nombre") : "";
                String lastname = request.getParameter("aPaterno") != null ? request.getParameter("aPaterno") : "";
                String surname = request.getParameter("aMaterno") != null ? request.getParameter("aMaterno") : "";
                String calle = request.getParameter("calle") != null ? request.getParameter("calle") : "";
                String colonia = request.getParameter("colonia") != null ? request.getParameter("colonia") : "";
                int municipio = request.getParameter("municipio") != null ? Integer.parseInt(request.getParameter("municipio")) : 0;

                BeanEmpleados beanEmpleados = new BeanEmpleados();

                beanEmpleados.setIdEmpleado(idRol);
                beanEmpleados.setNombreEmpleados(nombre);
                beanEmpleados.setaPaterno(lastname);
                beanEmpleados.setaMaterno(surname);
                beanEmpleados.setCalle(calle);
                beanEmpleados.setColonia(colonia);
                beanEmpleados.setMunicipio(municipio);

               /* beanUsers.setEmail(email);
                beanUsers.setPassword(contraseña);
                beanRole.setIdRol(rol);
                beanUsers.setNumberrol(beanRole);*/
                if (new DaoEmpleados().create(beanEmpleados)) {
                    request.setAttribute("message", "Usuario registrado correctamente");

                } else {
                    request.setAttribute("message", "Usuario no registrado");
                }
                //daoUsers.create(beanUsers);
                request.getRequestDispatcher("/views/admin/Admin.jsp").forward(request, response);
                break;
            case "findall":
                break;
          /*  case "update":
                beanAdmin.setName(request.getParameter("nombreAdmin"));
                beanAdmin.setLastname(request.getParameter("apellidoPatAdmin"));
                beanAdmin.setSurname(request.getParameter("apellidoMatAdmin"));
                beanAdmin.setStreet(request.getParameter("calleAdmin"));
                beanAdmin.setSuburb(request.getParameter("coloniaAdmin"));
                beanAdmin.setMunicipality(Integer.parseInt(request.getParameter("municipio")));

                boolean flag = daoAdmin.update(beanAdmin);
                if(flag){
                    CONSOLE.error ("Se ha actualizado satisfactoriamente");
                }else{
                    CONSOLE.error ("No se ha podido actualizar");
                }
                break;
            case "delete" :
                /*int idadmin = Integer.parseInt(request.getParameter("id"));
                daoAdmin.delete(idadmin);
                request.getRequestDispatcher("/views/Admin.jsp").forward(request, response);
                break;*/

               /* int id2 = Integer.parseInt(request.getParameter("IdAdmin"));
                if(new DaoAdmin().delete(id2)){
                    request.setAttribute("message", "Usuario eliminado correctamente");
                } else {
                    request.setAttribute("message", "Usuario no eliminado");
                }
                doGet(request, response);
                break;

            case "AdminById":

                int id = Integer.parseInt(request.getParameter("idAdmin"));
                request.setAttribute("administrador", new DaoAdmin().findById(id));
                request.getRequestDispatcher("/views/Admin.jsp").forward(request, response);
                break;
*/
        }
    }
    private void write (HttpServletResponse response, Map < String, Object > map) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(map));
    }
}