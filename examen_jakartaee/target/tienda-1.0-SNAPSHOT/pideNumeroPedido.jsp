<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda","root", "user");
    Statement s = conexion.createStatement();

    ResultSet listado = s.executeQuery ("SELECT * FROM pedido");
%>
<table>
    <tr><th>Código</th><th>Nombre</th><th>Estatura</th><th>Edad</th><th>Localidad</th></tr>
    <%
        Integer codigoPedidoADestacar = (Integer)session.getAttribute("codigoPedidoADestacar");
        String claseDestacar = "";
        while (listado.next()) {
            claseDestacar = (codigoPedidoADestacar != null
                    && codigoPedidoADestacar ==listado.getInt("codigo_pedido") ) ?
                    "destacar" :  "";
    %>


    <tr class="<%= claseDestacar%>" >
        <td >
            <%=listado.getInt("codigo_pedido")%>
        </td>
        <td><%=listado.getDate("fecha_pedido")%>
        </td>
        <td><%=listado.getDate("fecha_esperada")%>
        </td>
        <td><%=listado.getDate("fecha_entrega")%>
        </td>
        <td><%=listado.getString("estado")%>
        </td>
        <td><%=listado.getString("comentarios")%>
        </td>
        <td><%=listado.getInt("codigo_cliente")%>
        </td>
        <td>
            <form method="get" action="borraPedido.jsp">
                <input type="hidden" name="codigo" value="<%=listado.getString("codigo_pedido") %>"/>
                <input type="submit" value="borrar">
            </form>
        </td>
    </tr>
    <%
        } // while
        conexion.close();
    %>
</table>
</body>
</html>