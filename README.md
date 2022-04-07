# PruebaTecnicaDesba

Buenos dias! Soy Franco Paris, esta es la resolucion a la prueba tecnica planteada:

Dentro encontraran entre otras cosas 2 instaladores, y dos ejecutables. La aplicacion no es necesario que sea instalada
    por lo que no es estrictamente necesario recurrir a los instaladores. 

En caso de optar por instalar el programa, realizar el mismo es simple y basta con seguir el proceso eligiendo cualquier ruta 
    que se quiera. esto generara un ejecutable, el mismo es perfectamente funcional con una aclaracion, debido a las restricciones
    para crear archivos en las carpetas de un programa instalado es altamente probable que no sea posible mostrar los errores, para
    solucionar eso basta con copiar dicho ejecutable y pegarlo en cualquier otro lugar, donde ya libre de la restriccion se podra
    crear el archivo "avisos.log" y guardar dentro del mismo los errores en la carga de archivo

Respecto a los 2 ejecutables/instaladores:
    Los mismos corresponden a 2 versiones del mismo programa con exactamente las mismas funcionalidades y forma de operar, con la 
    unica diferencia de que uno funciona utilizando una base de datos con MySQL (donde poseo mucha mas experiencia, por lo cual el
    el MVP del producto fue realizado alli por cuestiones de velocidad) y el otro con MsSQL (Aqui soy nuevo, pero fue divertido
    realizarlo). Los nombres indican respectivamente que base de datos utilizan.

Datos para una correcta ejecucion del programa:

    La ejecucion sobre MySQL requiere de una conexion con los datos: usuario "root"
                                                                     contraseña "Nutriarosa7"
                                                                     puerto localhost:3306

    Esta version del programa no requiere nada mas que una conexion exitosa con los datos mencionados arriba ya que crea de manera 
        automática la base de datos requerida junto con tablas y demas cosas.


    La ejecucion sobre MsSQL requiere de una conexion con los datos: usuario "sa"
                                                                     contraseña "yourStrong(!)Password"
                                                                     puerto localhost:1433

    Esta version del programa SI requiere de la creacion de la base de datos de manera manual llamada "Products", puede realizar esto
    ejecutando lo siguiente :

                                CREATE DATABASE Products;
                                USE [master]
                                GO
                                ALTER DATABASE [Products] SET AUTO_CLOSE OFF;
                                GO

    Esto es necesario debido a que, desde el dia de ayer 06/04/2022 me encuentro intentando unicamente la creacion de dicha base de 
        manera automatica desde el programa, con resultados infructuosos.

        Agruego ademas el comando utilizado para correr el motor de SQLServer a fin de facilitar una correcta conexion:

        docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=yourStrong(!)Password" -e "MSSQL_PID=Express" -p 1433:1433 -d mcr.microsoft.com/mssql/server:2019-latest


    Como funciona el programa:
        Al iniciarlo se abrira de manera automatica la interfaz, allí veran 3 botones: seleccione archivos, Grabar Cambio y cancelar
            Los mismos realizan exactamente lo que su nombre indica, pero, como informacion adicional el boton cancelar NO realiza 
            un Rollback en la base de datos, si no que quita el archivo seleccionado para subirse. Al momento de seleccionar archivos
            es requisito tener un archivo .csv, el mismo para ser correctamente cargado debera tener el formato "codigoPLU(En numeros),
            Marca,Descripcion,Unidades en stock (En numeros), precio (En numeros)"
        Finalizada la ejecucion que demora unos segundos aparecera en la interfaz texto, el mismo indicara si hubo errores junto con un
            boton para abrir un block de notas donde ver los errores en los productos, o en caso de no tener ningun problema el mismo 
            señalara que no hubo errores como tal. 
        En el caso de tener un producto con el codigo PLU de otro producto el mismo sera actualizado de manera automatica.
        La ruta del archivo puede ser vista en el costado derecho del boton "selecione archivo", aqui mismo se vera un aviso en caso de
            no tener un archivo seleccionado.

-----------------------------------------------------------------------------------------------------------------------------------------

    Esto es todo, por supuesto estoy a disposicion de responder preguntas sobre el proyecto o demas, y adicionalmente agradecere recibir 
        feedback sobre el mismo continue o no con el proceso de seleccion. Fue muy divertido de realizar ya que me permitio salir un poco
        de mi zona de confort que esta un poco mas enfocada al desarrollo web y en otra base de datos.

    Contactame! email: parisjjfranco@gmail.com          Celular: +5491160479048         linkedin:https://www.linkedin.com/in/paris-franco/

    