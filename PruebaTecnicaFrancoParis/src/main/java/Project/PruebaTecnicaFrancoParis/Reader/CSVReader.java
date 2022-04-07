package Project.PruebaTecnicaFrancoParis.Reader;

import Project.PruebaTecnicaFrancoParis.ConfigurationJDBC;
import Project.PruebaTecnicaFrancoParis.Dao.ProductDao;
import Project.PruebaTecnicaFrancoParis.Model.Product;
import Project.PruebaTecnicaFrancoParis.MyException;
import Project.PruebaTecnicaFrancoParis.Service.ProductService;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CSVReader {                                //Clase encargada de cargar y mostrar errores ocurridos
    public static final String separator = ",";

    Integer errorCounter = 0;

    private static final Logger logger = Logger.getLogger(String.valueOf(CSVReader.class));

    public void reader(String fileLocation) {

        ProductService productService = new ProductService(new ProductDao(new ConfigurationJDBC()));

        BufferedReader bufferLectura = null;


        try {
            bufferLectura = new BufferedReader(new FileReader(fileLocation));

            String line = bufferLectura.readLine();

            Integer counter = 1;                        //Este es el contador de las filas del archivo seleccionado

            line = bufferLectura.readLine();
            counter++;

            while (line != null) {

                String[] campos = line.split(separator);        //Separamos cada parte de la linea del archivo
                                                                //y se coloca en una collection
                if (campos.length == 0){                        //Si no hay campos significa q ya no hay productos
                    break;                                      // y sale del bucle
                }

                System.out.println(Arrays.toString(campos));

                try {                       //Aqui se manejan los errores personalizados como la falta de algun dato,
                                            // a valor default del mismo
                    if (campos[1] == "") {
                        logger.info("Falta la marca del producto en la linea " + counter + " del archivo");
                        line = bufferLectura.readLine();
                        counter++;
                        errorCounter++;
                        throw new MyException(1);
                    } else if (campos[2] == "") {
                        logger.info("Falta la descripcion del producto en la linea " + counter + " del archivo");
                        line = bufferLectura.readLine();
                        counter++;
                        errorCounter++;
                        throw new MyException(2);
                    } else if (campos[3] == "") {
                        campos[3] = "0";
                    } else if (productService.searchUPC(Long.parseLong(campos[0])).isPresent()) {           //Aqui si el producto existe
                        Product producto = new Product(Long.parseLong(campos[0]), campos[1], campos[2],     //realizara un update del mismo
                                Integer.parseInt(campos[3]), Double.parseDouble(campos[4]));                // sin cambiar el codigo del
                                                                                                            // producto
                        productService.update(producto);
                        line = bufferLectura.readLine();
                        counter++;
                    } else {                                                                                //Si no hay errores y el producto
                        Product producto = new Product(Long.parseLong(campos[0]), campos[1], campos[2],     // es nuevo para la base de datos
                                Integer.parseInt(campos[3]), Double.parseDouble(campos[4]));                // se crea el objeto y se guarda

                        productService.save(producto);

                        System.out.println("se creo el objeto " + campos[1]);
                        line = bufferLectura.readLine();
                        counter++;
                    }
                } catch (NumberFormatException nfe) {                                               //Aqui tenemos errores mas
                    logger.info("Falta el codigo PLU o el mismo no es correcto para: "              // graves q afectan directamente
                            + campos[2] + " en la linea " + counter + " del archivo"/*, nfe*/);     // la carga de datospara facilitar la lectura
                    line = bufferLectura.readLine();                                                // del usuario se a decidido no mostrar
                    counter++;                                                                      // completo el codigo de error y cambiarlo
                    errorCounter++;                                                                 // por una oracion con informacion mas legible
                } catch (ArrayIndexOutOfBoundsException aioobe) {
                    logger.info("falta el precio o el mismo no es correcto para:" + campos[2] +
                            " en la linea " + counter + " del archivo"/*, aioobe*/);
                    line = bufferLectura.readLine();
                    counter++;
                    errorCounter++;
                } catch (MyException me) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Integer showErrorCounter(){
        return errorCounter;
    }  //Aqui se muestra la suma de errores
}