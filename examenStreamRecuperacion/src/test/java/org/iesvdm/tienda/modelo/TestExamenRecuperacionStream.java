package org.iesvdm.tienda.modelo;

import java.math.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;
import org.iesvdm.tienda.modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class TestExamenRecuperacionStream {

    @Test
    void testSkeletonCliente() {

        ClienteHome clienteHome = new ClienteHome();

        try {
            clienteHome.beginTransaction();

            List<Cliente> list = clienteHome.findAll();

            //TODO STREAMS
            list.stream().collect(toList());

            Assertions.assertEquals(36, list.stream().count());

            clienteHome.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
            clienteHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    @Test
    void testSkeletonPedido() {

        PedidoHome pedidoHome = new PedidoHome();

        try {
            pedidoHome.beginTransaction();

            List<Pedido> list = pedidoHome.findAll();

            //TODO STREAMS
            list.stream().collect(toList());

            Assertions.assertEquals(115, list.stream().count());

            pedidoHome.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
            pedidoHome.rollbackTransaction();
            throw e; // or display error message
        }
    }
    @Test
    void testSkeletonPago() {

        PagoHome pagoHome = new PagoHome();

        try {
            pagoHome.beginTransaction();

            List<Pago> list = pagoHome.findAll();

            //TODO STREAMS
            list.stream().collect(toList());

            Assertions.assertEquals(26, list.stream().count());

            pagoHome.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
            pagoHome.rollbackTransaction();
            throw e; // or display error message
        }
    }
    /**
     * 1. Devuelve un listado con los distintos estados por los que puede pasar un pedido, sin repetición.
     */
    @Test
    void test1() {
        PedidoHome pedidoHome = new PedidoHome();

        try {
            pedidoHome.beginTransaction();

            List<Pedido> list = pedidoHome.findAll();

            //TODO STREAMS
            List<String> estados = list.stream()
                            .map(pedido -> pedido.getEstado())
                            .distinct()
                            .collect(toList());

            estados.stream().forEach(System.out::println);

            pedidoHome.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
            pedidoHome.rollbackTransaction();
            throw e; // or display error message
        }
    }


    /**
     * 2.Devuelve un listado con el código de cliente de aquellos clientes que realizaron algún pago en 2008.
     * Tenga en cuenta que deberá eliminar aquellos códigos de cliente que aparezcan repetidos.
     */
    @Test
    void test2() {
        ClienteHome clienteHome = new ClienteHome();

        try {
            clienteHome.beginTransaction();

            List<Cliente> list = clienteHome.findAll();

            //TODO STREAMS
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date primerDia2008 = sdf.parse("2008-01-01");
            Date ultimoDia2008 = sdf.parse("2008-12-31");

            List<Integer> codigoClientes = list.stream()
                    .filter(cliente -> cliente.getPagos().stream()
                            .anyMatch( pago -> pago.getFechaPago().after(primerDia2008) && pago.getFechaPago().before(ultimoDia2008)))
                    .map(Cliente::getCodigoCliente)
                    .distinct()
                    .toList();

            codigoClientes.forEach(System.out::println);



            clienteHome.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
            clienteHome.rollbackTransaction();
            throw e; // or display error message
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 3. Devuelve un listado con el código de pedido, código de cliente, fecha esperada
     * y fecha de entrega de los pedidos que no han sido entregados a tiempo.
     * NOTAS:
     * Revisa los campos de la tabla pedido haciendo doble click en el panel de Database.
     * Razona sobre los campos de la tabla.
     * Puede que tengas que tener cuidado con los NULL de la tabla o puede que no.
     */
    @Test
    void test3() {
        PedidoHome pedidoHome = new PedidoHome();

        try {
            pedidoHome.beginTransaction();

            List<Pedido> list = pedidoHome.findAll();

            //TODO STREAMS


            pedidoHome.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
            pedidoHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 4. Devuelve un listado con el código de pedido, código de cliente, fecha esperada
     * y fecha de entrega de los pedidos cuya fecha de entrega
     * ha sido al menos dos días antes de la fecha esperada.
     * NOTAS: fecha de entrega null significa que no se ha entregado.
     */
    @Test
    void test4() {

        //Prueba de aritmética de fechas
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Utilizando la fecha de hoy
        c.add(Calendar.DATE, 2); // Sumando 2 días
        String output = sdf.format(c.getTime());
        System.out.println(output);
        //

        PedidoHome pedidoHome = new PedidoHome();

        try {
            pedidoHome.beginTransaction();

            List<Pedido> list = pedidoHome.findAll();

            //TODO STREAMS
            list.stream().filter(pedido -> {
            //Completa con la prueba de aritmética de fechas previa el filtro y el stream.
                return false;
            });

            pedidoHome.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
            pedidoHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 5. Devuelve un listado con todos los pagos que se realizaron en el año 2008 mediante Paypal.
     * Ordene el resultado de mayor a menor según importe de pago.
     */
    @Test
    void test5() {
        PagoHome pagoHome = new PagoHome();

        try {
            pagoHome.beginTransaction();

            List<Pago> list = pagoHome.findAll();

            //TODO STREAMS
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date ultimoDia2007 = sdf.parse("2007-12-31");
            Date ultimoDia2008 = sdf.parse("2008-12-31");

            List<Pago> pago2008 = list.stream().filter(pago -> pago.getFechaPago().after(ultimoDia2007) && pago.getFechaPago().before(ultimoDia2008) && pago.getFormaPago().equals("PayPal"))
                    .toList();

            pago2008.forEach(System.out::println);


            pagoHome.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
            pagoHome.rollbackTransaction();
            throw e; // or display error message
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 6. Muestra el nombre de las regiones de los clientes que hayan realizado pagos.
     */
    @Test
    void test6() {
        ClienteHome clienteHome = new ClienteHome();

        try {
            clienteHome.beginTransaction();

            List<Cliente> list = clienteHome.findAll();

            //TODO STREAMS
            List<String> clienteRegion = list.stream()
                    .filter(cliente -> !cliente.getPagos().isEmpty())
                    .map(Cliente::getRegion)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();

            clienteRegion.forEach(System.out::println);

            clienteHome.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
            clienteHome.rollbackTransaction();
            throw e; // or display error message
        }
    }


    /**
     * 7. ¿Cuántos clientes existen con domicilio en la ciudad de Madrid?
     */
    @Test
    void test7() {
        ClienteHome clienteHome = new ClienteHome();

        try {
            clienteHome.beginTransaction();

            List<Cliente> list = clienteHome.findAll();

            //TODO STREAMS
            List<Long> clientesMadrid = Collections.singletonList(list.stream()
                    .filter(cliente -> cliente.getCiudad().equals("Madrid"))
                    .count());

            clientesMadrid.forEach(System.out::println);

            clienteHome.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
            clienteHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 8. Devuelve el nombre del cliente con mayor límite de crédito.
     */
    @Test
    void test8() {
        ClienteHome clienteHome = new ClienteHome();

        try {
            clienteHome.beginTransaction();

            List<Cliente> list = clienteHome.findAll();

            //TODO STREAMS
            String clientemayorCredito = list.stream()
                    .max(Comparator.comparing(Cliente::getLimiteCredito))
                    .map(Cliente::getNombreCliente)
                    .toString();

            System.out.println(clientemayorCredito);

            clienteHome.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
            clienteHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     *  9. Devuelve un listado con el número de clientes que tiene cada país.
     */
    @Test
    void test9() {
        ClienteHome clienteHome = new ClienteHome();

        try {
            clienteHome.beginTransaction();

            List<Cliente> list = clienteHome.findAll();

            //TODO STREAMS
            List<Long> numeroClientesPais = Collections.singletonList(list.stream()
                    .map(cliente -> cliente.getPais())
                    .count());

                    numeroClientesPais.stream().forEach(System.out::println);

            clienteHome.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
            clienteHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

    /**
     * 10. Calcula la fecha del primer y último pago realizado por cada uno de los clientes.
     * El listado deberá mostrar el nombre y los apellidos de cada cliente.
     */
    void test10() {
        ClienteHome clienteHome = new ClienteHome();

        try {
            clienteHome.beginTransaction();

            List<Cliente> list = clienteHome.findAll();

            //TODO STREAMS

            clienteHome.commitTransaction();
        } catch (RuntimeException e) {
            e.printStackTrace();
            clienteHome.rollbackTransaction();
            throw e; // or display error message
        }
    }

}
