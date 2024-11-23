package com.alura.libreria2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "nombre_libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private String fechaDeNacimiento;
    private String fechaDeMuerte;
    private String idiomas;
    private double numeroDeDescargas;

    public Libro(){}

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        if (!datosLibros.autor().isEmpty()) {
            DatosAutor autor = datosLibros.autor().get(0); // Tomamos el primer autor.
            this.autor = autor.nombre();
            this.fechaDeNacimiento = autor.fechaDeNacimiento() != null ? autor.fechaDeNacimiento() : "Desconocida";
            this.fechaDeMuerte = autor.fechaDeMuerte() != null ? autor.fechaDeMuerte() : "Desconocida";
        } else {
            this.autor = "Autor desconocido";
            this.fechaDeNacimiento = "Desconocida";
            this.fechaDeMuerte = "Desconocida";
        }
        this.idiomas = String.join(", ", datosLibros.idiomas());
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(String fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
    @Override
    public String toString() {
        return String.format("""
                Titulo: %s
                Autor: %s
                Fecha de Nacimiento: %s
                Fecha de Muerte: %s
                Idioma: %s
                Numero de descargas: %.0f
                """, titulo, autor, fechaDeNacimiento, fechaDeMuerte, idiomas, numeroDeDescargas);
    }
}
