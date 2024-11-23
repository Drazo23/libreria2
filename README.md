<h1> Biblioteca de libros</h1>

Programa que le muestra al usuario un menu por consola donde tiene las siguientes opciones:

1) Buscar libro por titulo.

2) Listar libros registrados.

3) Listar autores registrados.

4) Listar autores vivos en un determinado año.

5) Listar libros por idioma.

0) Salir.

Al usuario elegir la opcion 1, se le pide que ingrese el nombre del libro que desea buscar. El programa busca el nombre del libro en la API de Gutendex trae la siguiente informacion al usuario:

-Titulo

-Autor

-Fecha de nacimiento

-Fecha de muerte

-Idioma

-Numero de descargas.

Esta informacion es guardada en una base de datos en pgAdmin de donde es traida la informacion de los libros consultados, esta informacion se lista cuando el usuario elige la opcion 2 del menu.

Si el usuario elige la opcion 3, se traera la informacion de todos los autores que se encuentran en la base de datos de la siguiente forma:

-Nombre

-Fecha de nacimiento

-Fecha de muerte

Si el usuario elige la opcion 4, se le solicitara que ingrese el año que desea consultar. posterior le aparecera el listado de todos los autores vivos que esten en la base de datos, de acuerdo al año indicado, y le aparecera la informacion en el mismo formato de la opcion anterior

Si el usuario elige la opcion 5 le aparecera el siguiente menu por consola:
-1) es-Español
-2) en-Inglés
-3) fr-Francés
-4) pt-Portugués

Segun la opcion que el usuario elija, se le mostrara el listado de todos los libros registrados en la base de datos que se encuentren en el idioma seleccionado.

El programa tambien tiene las siguientes caracteristicas

-No permite registrar el mismo libro dos veces

-Si el usuario ingresa el nombre de un libro que no se encuentra en la API, se le informara que el libro no fue encontrado

-Si el usuario ingresa una opcion que no se encuentra en el menu tanto el inicial como el de idiomas, se le comunicara all usuario que la opcion no es vali y que debe elegir una opcion valida. 




