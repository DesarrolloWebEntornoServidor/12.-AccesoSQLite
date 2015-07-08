<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>consultaEmpleado</title>
    </head>
    <body>
        <h1> Proyecto: AccesoASQLite</h1>
        <h3>La base de datos ha sido creada</h3>
        <fieldset style="width:400px;">
            <legend>Consulta de empleados</legend>
            <form name="f1" action="GestionDB" method="post">
                Introduce el empno <input name="empno" type="text"/> <br />
                (7839, 7698, 7499)<br/><br/>
                <input type="submit" />
            </form>
        </fieldset>
    </body>
</html>
