<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado Pedidos</title>
</head>
<body>
<h1>Listado de Pedidos</h1>

<td>
    <form method="get" action="crearPedido.jsp">
        <input type="hidden" name="codigo" value="  "/>
        <input type="submit" value="Crear">
    </form>
</td></tr>

<%
    //CARGA DEL DRIVER Y PREPARACIÓN DE LA CONEXIÓN CON LA BBDD
    //						v---------UTILIZAMOS LA VERSIÓN MODERNA DE LLAMADA AL DRIVER, no deprecado
    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda","root", "user");
    //UTILIZAR STATEMENT SÓLO EN QUERIES NO PARAMETRIZADAS.
    Statement s = conexion.createStatement();
    ResultSet listado = s.executeQuery ("SELECT * FROM pedido");

    while (listado.next()) {
        out.println("<tr><td>"+listado.getInt("codigo_pedido") + "</td>" + "<br>");
        out.println("<td>" + listado.getDate("fecha_pedido") + "</td>" + "<br>");
        out.println("<td>" + listado.getDate("fecha_esperada") + "</td>" + "<br>");
        out.println("<td>" + listado.getDate("fecha_entrega") + "</td>" + "<br>");
        out.println("<td>" + listado.getString("estado") + "</td>" + "<br>");
        out.println("<td>" + listado.getInt("codigo_cliente") + "</td>" + "<br>");
    }
    listado.close();
    s.close();
    conexion.close();
%>

<td>
    <form method="get" action="borraPedido.jsp">
        <input type="hidden" name="codigo" value="<%=listado.getString("codigo_pedido") %>"/>
        <input type="submit" value="borrar">
    </form>
</td></tr>

<td>
    <form method="get" action="borraPedido.jsp">
        <input type="hidden" name="codigo" value="<%=listado.getString("codigo_pedido") %>"/>
        <input type="submit" value="Editar">
    </form>
</td></tr>

<td>
    <form method="get" action="borraPedido.jsp">
        <input type="hidden" name="codigo" value="<%=listado.getString("codigo_pedido") %>"/>
        <input type="submit" value="borrar">
    </form>
</td></tr>

</body>
</html>