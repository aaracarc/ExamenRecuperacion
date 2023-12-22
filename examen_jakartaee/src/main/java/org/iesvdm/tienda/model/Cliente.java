package org.iesvdm.tienda.model;

import java.util.Objects;

/**
 * POJO o BEAN QUE REPRESENTA LA TABLA socio
 */
public class Cliente {

    private int codigo_cliente;
    private String nombre_cliente;
    private String nombre_contacto;
    private String apellido_contacto;
    private String telefono;
    private String linea_direccion;
    private String ciudad;
    private String region;
    private String pais;
    private String codigo_postal;
    private double limite_credito;

    public Cliente() {
    }

    public Cliente(int codigo_cliente, String nombre_cliente, String nombre_contacto, String apellido_contacto, String telefono, String linea_direccion, String ciudad, String region, String pais, String codigo_postal, double limite_credito) {
        this.codigo_cliente = codigo_cliente;
        this.nombre_cliente = nombre_cliente;
        this.nombre_contacto = nombre_contacto;
        this.apellido_contacto = apellido_contacto;
        this.telefono = telefono;
        this.linea_direccion = linea_direccion;
        this.ciudad = ciudad;
        this.region = region;
        this.pais = pais;
        this.codigo_postal = codigo_postal;
        this.limite_credito = limite_credito;
    }

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getNombre_contacto() {
        return nombre_contacto;
    }

    public void setNombre_contacto(String nombre_contacto) {
        this.nombre_contacto = nombre_contacto;
    }

    public String getApellido_contacto() {
        return apellido_contacto;
    }

    public void setApellido_contacto(String apellido_contacto) {
        this.apellido_contacto = apellido_contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLinea_direccion() {
        return linea_direccion;
    }

    public void setLinea_direccion(String linea_direccion) {
        this.linea_direccion = linea_direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public double getLimite_credito() {
        return limite_credito;
    }

    public void setLimite_credito(double limite_credito) {
        this.limite_credito = limite_credito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente socio = (Cliente) o;
        return codigo_cliente == socio.codigo_cliente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo_cliente);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigo_cliente=" + codigo_cliente +
                ", nombre_cliente='" + nombre_cliente + '\'' +
                ", nombre_contacto='" + nombre_contacto + '\'' +
                ", apellido_contacto='" + apellido_contacto + '\'' +
                ", telefono='" + telefono + '\'' +
                ", linea_direccion='" + linea_direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", region='" + region + '\'' +
                ", pais='" + pais + '\'' +
                ", codigo_postal='" + codigo_postal + '\'' +
                ", limite_credito=" + limite_credito +
                '}';
    }
}