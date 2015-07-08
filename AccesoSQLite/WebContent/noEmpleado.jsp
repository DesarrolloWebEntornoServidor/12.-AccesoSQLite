<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>noEmpleado</title>
    </head>
    <body>
        <h1> Proyecto: AccesoASQL</h1>
        <h3>NO se encuentra el empleado solicitado</h3>
        <fieldset style="width:400px;">
            <legend>Consulta de empleados</legend>
            <form name="f1" action="GestionDB" method="post">
                Introduce el empleado <input name="empno" type="text" value="7839" /> <br /><br />
                <input type="submit" />
            </form>
        </fieldset>
    </body>
</html>
