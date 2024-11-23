package com.alura.libreria2.repository;

import com.alura.libreria2.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("""
           SELECT l FROM Libro l
           WHERE (CAST(l.fechaDeNacimiento AS int) <= :anio OR l.fechaDeNacimiento = 'Desconocida')
             AND (l.fechaDeMuerte = 'Desconocida' OR CAST(l.fechaDeMuerte AS int) > :anio)
           """)
    List<Libro> findAutoresVivosEnAnio(@Param("anio") int anio);

}
