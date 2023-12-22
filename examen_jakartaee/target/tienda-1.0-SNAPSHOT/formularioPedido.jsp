<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>Introduzca los datos del nuevo pedido:</h2>
<form method="post" action="crearPedido.jsp">
  Nº pedido <input type="text" name="codigo_pedido"/></br>
  Fecha Pedido <input type="date" name="fecha_pedido"/></br>
  Fecha Esperada<input type="date" name="fecha_esperada"/></br>
  Fecha Entrega <input type="date" name="fecha_entrega"/></br>
  Estado <input type="text" name="estado"/></br>
  Comentarios <input type="text" name="comentarios"/></br>
  Nº cliente <input type="text" name="codigo_cliente"/></br>
  <input type="submit" value="Aceptar">
</form>

<%
  String error = (String)session.getAttribute("error");
  if ( error != null) {
%>
<span style="background-color: red;color: yellow"><%=error%></span>
<%
    session.removeAttribute("error");
  }
%>
</body>
</html>