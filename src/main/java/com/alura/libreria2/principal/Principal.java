package com.alura.libreria2.principal;

import com.alura.libreria2.model.Datos;
import com.alura.libreria2.model.DatosLibros;
import com.alura.libreria2.model.Libro;
import com.alura.libreria2.repository.LibroRepository;
import com.alura.libreria2.service.ConsumoAPI;
import com.alura.libreria2.service.ConvierteDator;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDator conversor = new ConvierteDator();
    private Scanner teclado = new Scanner(System.in);
    private final List<Libro> librosConsultados = new ArrayList<>();
    private LibroRepository repositorio;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {

        while (true) {

            System.out.println("Elija una opcion");
            String opciones = """
                    1) Buscar libro por titulo.
                    2) Listar libros registrados.
                    3) Listar autores registrados.
                    4) Listar autores vivos en un determinado año.
                    5) Listar libros por idioma.
                    0) Salir.
                    """;
            System.out.println(opciones);
            int opcion = teclado.nextInt();
            teclado.nextLine();
            if (opcion == 0) {
                System.out.println("Saliste del programa");
                break;
            }
            switch (opcion) {
                case 1:
                    //buscar libro por nombre
                    System.out.println("Ingrese el nombre del libro.");
                    var tituloLibro = teclado.nextLine();
                    String json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
                    var datosBusqueda = conversor.obtenerDatos(json, Datos.class);


                    Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                            .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                            .findFirst();
                    if (libroBuscado.isPresent()) {
                        Libro libro = new Libro(libroBuscado.get());
                        System.out.println("Libro encontrado");
                        System.out.println(libro);
                        //repositorio.save(libro);
                        try {
                            repositorio.save(libro);
                            System.out.println("Libro resgistrado");
                        }catch (DataIntegrityViolationException e){
                            System.out.println("Ese libro ya esta registrado. No se puede registrar un mismo libro mas de una vez");
                        }catch (Exception e){
                            System.out.println("Ocurrio un error inesperadoe" + e.getMessage());
                        }

                    } else {
                        System.out.println("Libro no encontrado");
                    }
                    break;

                case 2:
                    System.out.println("libros registrados");
                    Iterable<Libro> librosGuardados = repositorio.findAll();
                    if (!librosGuardados.iterator().hasNext()) {
                        System.out.println("No hay libros registrados");
                    } else {
                        for (Libro libro : librosGuardados) {
                            System.out.println(libro);
                            System.out.println();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Lista de autore registrados");
                    Iterable<Libro> libros = repositorio.findAll();
                    if (!libros.iterator().hasNext()){
                        System.out.println("No hay autores registrados");
                    }else {
                        for (Libro libro : libros){
                            System.out.printf("""
                                   Autor: %s
                                   Fecha de Nacimiento: %s
                                   Fecha de Muerte: %s
                                   """,
                                    libro.getAutor(),
                                    libro.getFechaDeNacimiento(),
                                    libro.getFechaDeMuerte());
                            System.out.println();
                        }
                    }
                    break;
                case 4:
                    System.out.println("Ingrese el año a consultar.");
                    int anio = teclado.nextInt();
                    teclado.nextLine();

                    List<Libro> autoreVivos = repositorio.findAutoresVivosEnAnio(anio);
                    if (autoreVivos.isEmpty()){
                        System.out.println("No se encontraron autores vivos en el año indicado");
                    }else {
                        System.out.println("Autores vivos en el año indicado");
                        for (Libro autor : autoreVivos){
                            System.out.printf("""
                                    Autor: %s
                                    Fecha de Nacimiento: %s
                                    Fecha de Muerte: %s
                                    """,
                                    autor.getAutor(),
                                    autor.getFechaDeNacimiento(),
                                    autor.getFechaDeMuerte());
                            System.out.println();
                        }
                    }
                    break;
                case 5:
                    System.out.println("Elija el numero correspondiente al idioma que desea");
                    String opcionesIdioma = """
                            1) es-Español
                            2) en-Inglés
                            3) fr-Francés
                            4) pt-Portugués
                            """;
                    System.out.println(opcionesIdioma);
                    int opcioneIdioma = teclado.nextInt();
                    teclado.nextLine();
                    String idiomaSeleccionado = switch (opcioneIdioma){
                        case 1-> "es";
                        case 2-> "en";
                        case 3-> "fr";
                        case 4-> "pt";
                        default -> null;
                    };
                    if (idiomaSeleccionado == null){
                        System.out.println("Opcion invalida");
                        break;
                    }
                    System.out.printf("Listado de libros en el idioma seleccionado", idiomaSeleccionado);
                    Iterable<Libro> libroPorIdioma = repositorio.findAll();
                    boolean hayResultados = false;
                    for (Libro libro: libroPorIdioma){
                        if (libro.getIdiomas().contains(idiomaSeleccionado)){
                            System.out.printf("""
                                    Título: %s
                                    Autor: %s
                                    Idioma: %s
                                    
                                    """,
                                    libro.getTitulo(),
                                    libro.getAutor(),
                                    libro.getIdiomas());
                            System.out.println();
                            hayResultados = true;

                        }
                    }
                    if (!hayResultados){
                        System.out.println();
                        System.out.println("No se encontraron libros para ese idioma.");
                    }
                    break;
                default:
                    System.out.println("Opcion no valida, intente nuevamente");

            }

        }
    }
}




