package org.iesvdm.tienda.dao;

import org.iesvdm.tienda.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAOImpl extends AbstractDAOImpl implements ClienteDAO {
    @Override
    public void create(Cliente cliente) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();


            //1 alternativas comentadas:
            //Ver también, AbstractDAOImpl.executeInsert ...
            //Columna fabricante.codigo es clave primaria auto_increment, por ese motivo se omite de la sentencia SQL INSERT siguiente.
            ps = conn.prepareStatement("INSERT INTO cliente (codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto, telefono, linea_direccion, ciudad, region, pais, codigo_postal, limite_credito) VALUES (?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, cliente.getNombre_cliente());
            ps.setString(idx++, cliente.getNombre_contacto());
            ps.setString(idx++, cliente.getApellido_contacto());
            ps.setString(idx++, cliente.getTelefono());
            ps.setString(idx++, cliente.getLinea_direccion());
            ps.setString(idx++, cliente.getCiudad());
            ps.setString(idx++, cliente.getRegion());
            ps.setString(idx++, cliente.getPais());
            ps.setString(idx++, cliente.getCodigo_postal());
            ps.setDouble(idx++, cliente.getLimite_credito());


            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de socio con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                cliente.setCodigo_cliente(rsGenKeys.getInt(1));

        } catch (SQLException | ClassNotFoundException  e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public List<Cliente> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Cliente> listCliente = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM cliente");
            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setCodigo_cliente(rs.getInt("codigo_cliente"));
                cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                cliente.setNombre_contacto(rs.getString("nombre_contacto"));
                cliente.setApellido_contacto(rs.getString("apellido_contacto"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setLinea_direccion(rs.getString("linea_direccion"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setRegion(rs.getString("region"));
                cliente.setPais(rs.getString("pais"));
                cliente.setCodigo_postal(rs.getString("codigo_postal"));
                cliente.setLimite_credito(rs.getDouble("limite_credito"));

                listCliente.add(cliente);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listCliente;

    }

    @Override
    public Optional<Cliente> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM cliente WHERE codigo_cliente = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setCodigo_cliente(rs.getInt("codigo_cliente"));
                cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                cliente.setNombre_contacto(rs.getString("nombre_contacto"));
                cliente.setApellido_contacto(rs.getString("apellido_contacto"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setLinea_direccion(rs.getString("linea_direccion"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setRegion(rs.getString("region"));
                cliente.setPais(rs.getString("pais"));
                cliente.setCodigo_postal(rs.getString("codigo_postal"));
                cliente.setLimite_credito(rs.getDouble("limite_credito"));

                return Optional.of(cliente);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();

    }

    @Override
    public void update(Cliente cliente) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE socio SET nombre_cliente = ?, nombre_contacto = ?, apellido_contacto = ?, telefono = ?, linea_direccion = ?, ciudad = ?, region = ?, pais = ?, codigo_postal = ?, limite_credito = ?  WHERE codigo_cliente = ?");
            int idx = 1;
            ps.setString(idx++, cliente.getNombre_cliente());
            ps.setString(idx++, cliente.getNombre_contacto());
            ps.setString(idx++, cliente.getApellido_contacto());
            ps.setString(idx++, cliente.getTelefono());
            ps.setString(idx++, cliente.getLinea_direccion());
            ps.setString(idx++, cliente.getCiudad());
            ps.setString(idx++, cliente.getRegion());
            ps.setString(idx++, cliente.getPais());
            ps.setString(idx++, cliente.getCodigo_postal());
            ps.setDouble(idx++, cliente.getLimite_credito());

            ps.setInt(idx++, cliente.getCodigo_cliente());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de cliente con 0 registros actualizados.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public void delete(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM cliente WHERE codigo_cliente = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de cliente con 0 registros eliminados.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }
}