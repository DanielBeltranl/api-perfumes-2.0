package com.perfulandia.perfulandia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;    
import lombok.Builder;



@Entity
@Table(name = "Envio")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Envio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;


    @Column (nullable=false)
    private String nombre;
    @Column (nullable=false)
    private String destino;
    @Column (nullable=false)
    private String estado;
    @Column (nullable=false)
    private String rutaOptima;
    @Column (nullable=false)
    private String origen;
    


}