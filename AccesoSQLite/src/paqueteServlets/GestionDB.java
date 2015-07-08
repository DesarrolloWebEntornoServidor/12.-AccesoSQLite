package paqueteServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paquetePojos.Empleado;

@WebServlet({"/GestionDB"})
public class GestionDB extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Connection conexion;    
    
    // El método init() solo se ejecuta una vez
    @Override
    public void init() throws ServletException {
	super.init();
	// Creacion de la BD tiger al arrancar el servlet
	String ruta=getServletContext().getRealPath("/");
    	CreacionDB cdb=new CreacionDB();
    	conexion=cdb.creaDB(ruta);
    	System.out.println("3. Conexión establecida");
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
        // Reenvío al formulario consultaEmpleado.jsp
        response.sendRedirect("consultaEmpleado.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
        response.setContentType("text/html");
    	try {

	    //  Consulta de empleado
	    Statement sentencia = conexion.createStatement();
            String empnoCadena = request.getParameter("empno");
            if (empnoCadena.equals(""))
                empnoCadena="0"; // Se puede reenviar al formulario
            int empno= Integer.parseInt(empnoCadena);
            ResultSet resultado =null;
            Empleado emple = consultaEnTablaEmp(sentencia, resultado, empno);
            System.out.println("4.Consulta realizada");
               		    
	    //  Presentación de resultados
            RequestDispatcher dispatcher;
            if (emple != null) {  // se encuentra un empleado
            	request.setAttribute("elEmpleado", emple);
            	String vista = "/visualizaEmpleado.jsp";
            	dispatcher = getServletContext().getRequestDispatcher(vista);
            	dispatcher.forward(request, response); 
	    } else {
	        String vista = "/noEmpleado.jsp";
            	dispatcher = getServletContext().getRequestDispatcher(vista);
            	dispatcher.forward(request, response); 
	    }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException p) {
            p.printStackTrace();
        }
    }

    // Funciones para la tabla Emp
  
    private Empleado consultaEnTablaEmp(Statement sentencia, ResultSet resultado, int empno) 
        throws SQLException, ParseException {
        String consultaSQL = "SELECT * FROM Emp WHERE empno = " + empno;
        resultado = sentencia.executeQuery(consultaSQL);
           
        if (resultado.next()) {  // se encuentra al empleado
            // Conversión de string a java.util.Date
            java.util.Date utilDate = new java.util.Date();
            String hiredate = resultado.getString("hiredate");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            utilDate = formato.parse(hiredate);
           
            return new Empleado(empno,
                                resultado.getString("ename"),
                                resultado.getString("job"),
                                utilDate,
                                resultado.getFloat("sal"),
                                resultado.getFloat("comm")
                       		);
        } else {  // no se encuentra al empleado
            return null;
	    }
    }
    
    private void listarTablaEmp(Connection conexion, Statement sentencia) throws SQLException {
        String consultaSQL = "SELECT * FROM Emp";
        sentencia = conexion.createStatement();
        try {
            sentencia.executeUpdate(consultaSQL);  
        }
        catch (Exception e){
            e.printStackTrace();
        }          
    }
}