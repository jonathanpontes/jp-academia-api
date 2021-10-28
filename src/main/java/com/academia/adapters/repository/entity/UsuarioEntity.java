package com.academia.adapters.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "USUARIO")
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String nome;
    private Double peso;
    private Double altura;
    private String sexo;
    private String email;
    private String telefone;
    private byte[] foto;
}
