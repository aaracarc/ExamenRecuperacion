<%@page import="java.sql.*" %>
<%@page import="java.util.Objects" %>
<%@ page import="java.io.IOException" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
  //CÓDIGO DE VALIDACIÓN
  boolean valida = true;
  int codigo_pedido = -1;
  Date fecha_pedido = null;
  Date fecha_esperada = null;
  Date fecha_entrega = null;
  String estado = null;
  String comentarios = null;
  int codigo_cliente = -1;

  // INICIO CODIGO VALIDACION

  boolean flagValidaCodigoPedido = false;
  boolean flagValidaFechaPedidoNull = false;
  boolean flagValidaFechaPedidoBlank = false;
  boolean flagValidaFechaEsperadaNull = false;
  boolean flagValidaFechaEsperadaBlank = false;
  boolean flagValidaFechaEntregaNull = false;
  boolean flagValidaFechaEntregaBlank = false;
  boolean flagValidaEstadoNull = false;
  boolean flagValidaEstadoBlank = false;
  boolean flagValidaCodigoCliente = false;

  try {
    codigo_pedido = Integer.parseInt(request.getParameter("codigo_pedido"));
    flagValidaCodigoPedido = true;

    Objects.requireNonNull(request.getParameter("fecha_pedido"));
    flagValidaFechaPedidoNull = true;
    if (request.getParameter("fecha_pedido").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
    flagValidaFechaPedidoBlank = true;
    fecha_pedido = Date.valueOf(request.getParameter("fecha_pedido"));

    Objects.requireNonNull(request.getParameter("fecha_esperada"));
    flagValidaFechaEsperadaNull = true;
    if (request.getParameter("fecha_esperada").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
    flagValidaFechaEsperadaBlank = true;
    fecha_esperada = Date.valueOf(request.getParameter("fecha_esperada"));

    Objects.requireNonNull(request.getParameter("fecha_entrega"));
    flagValidaFechaEntregaNull = true;
    if (request.getParameter("fecha_entrega").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
    flagValidaFechaEntregaBlank = true;
    fecha_entrega = Date.valueOf(request.getParameter("fecha_entrega"));

    Objects.requireNonNull(request.getParameter("estado"));
    flagValidaEstadoNull = true;
    if (request.getParameter("estado").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
    flagValidaEstadoBlank = true;
    estado = (request.getParameter("estado"));

    codigo_cliente = Integer.parseInt(request.getParameter("codigo_cliente"));
    flagValidaCodigoCliente = true;

  } catch (Exception ex) {
    ex.printStackTrace();

    if(!flagValidaCodigoPedido) {
      session.setAttribute("error", "Error en codigo.");
    } else if (!flagValidaFechaPedidoNull || !flagValidaFechaPedidoBlank) {
      session.setAttribute("error", "Error en fecha Pedido.");
    } else if (!flagValidaFechaEsperadaNull || !flagValidaFechaEsperadaBlank) {
      session.setAttribute("error", "Error en fecha esperada.");
    } else if (!flagValidaFechaEntregaNull || !flagValidaFechaEntregaBlank) {
      session.setAttribute("error", "Error en fecha entrega.");
    } else if (!flagValidaEstadoNull || !flagValidaEstadoBlank) {
      session.setAttribute("error", "Error en estado.");
    } else if (!flagValidaCodigoCliente) {
      session.setAttribute("error", "Error en codigo cliente.");
    }

    valida = false;
  }
  //FIN CÓDIGO DE VALIDACIÓN

  if (valida) {

    Connection conn = null;
    PreparedStatement ps = null;
// 	ResultSet rs = null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "user");

      String sql = "INSERT INTO pedido VALUES ( " +
              "?, " + //codigo_pedido
              "?, " + //fecha_pedido
              "?, " + //fecha_esperada
              "?, " + //fecha_entrega
              "?, " + //estado
              "?, " + //comentarios
              "?)"; //codigo_cliente

      ps = conn.prepareStatement(sql);
      int idx = 1;
      ps.setInt(idx++, codigo_pedido);
      ps.setDate(idx++, fecha_pedido);
      ps.setDate(idx++, fecha_esperada);
      ps.setDate(idx++, fecha_entrega);
      ps.setString(idx++, estado);
      ps.setString(idx++, comentarios);
      ps.setInt(idx++, codigo_cliente);

      int filasAfectadas = ps.executeUpdate();
      System.out.println("PEDIDOS GRABADOS:  " + filasAfectadas);


    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      try {
        ps.close();
      } catch (Exception e) { /* Ignored */ }
      try {
        conn.close();
      } catch (Exception e) { /* Ignored */ }
    }

    session.setAttribute("codigoPedidoADestacar", codigo_pedido);
    response.sendRedirect("pideNumeroPedido.jsp");

  } else {
    //out.println("Error de validación!");
    response.sendRedirect("formularioPedido.jsp");
  }
%>

</body>
</html>