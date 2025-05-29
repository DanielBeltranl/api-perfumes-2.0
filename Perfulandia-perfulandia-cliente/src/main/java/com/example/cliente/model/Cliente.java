package com.example.cliente.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {

    @Id
    @Column (name = "id_cliente")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @Column (name = "run_cliente", nullable = false)
    private String run_cliente;

    @Column(name = "nombre_cliente", nullable = false )
    private String nombre_cliente;

    @Column (name = "apellido_cliente", nullable = false)
    private String apellido_cliente;

    @Column (name = "direccion_cliente", nullable = false)
    private String direccion_cliente;

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getRun_cliente() {
        return run_cliente;
    }

    public void setRun_cliente(String run_cliente) {
        this.run_cliente = run_cliente;
    }
}
