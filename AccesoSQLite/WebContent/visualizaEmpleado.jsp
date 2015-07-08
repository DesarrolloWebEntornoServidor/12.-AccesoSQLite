<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="paquetePojos.Empleado"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Visualización de empleado</title>
    </head>
    <body>
        <h1>Proyecto: AccesoASQLite</h1>
        <h3>Datos del empleado</h3>

        <%
          Empleado emple = (Empleado) request.getAttribute("elEmpleado"); 
          out.println("Código: " + emple.getEmpno() + "<br />");
          out.println("Apellidos: " + emple.getEname() + "<br />");
          out.println("Oficio: " + emple.getJob() + "<br />");
          out.println("Fecha contratación: " + emple.getHiredate() + "<br />");  
        %>
	<br/>
        <a href="GestionDB">Realizar otra consulta</a>
    </body>
</html>