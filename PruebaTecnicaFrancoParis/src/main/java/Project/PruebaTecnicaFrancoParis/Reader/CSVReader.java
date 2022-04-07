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

public class CSVReader {
    public static final String separator = ",";

    Integer errorCounter = 0;

    private static final Logger logger = Logger.getLogger(String.valueOf(CSVReader.class));

    public void reader(String fileLocation) {

        ProductService productService = new ProductService(new ProductDao(new ConfigurationJDBC()));

        BufferedReader bufferLectura = null;


        try {
            bufferLectura = new BufferedReader(new FileReader(fileLocation));

            String line = bufferLectura.readLine();

            Integer counter = 1;

            line = bufferLectura.readLine();
            counter++;

            while (line != null) {

                String[] campos = line.split(separator);

                if (campos.length == 0){
                    break;
                }

                System.out.println(Arrays.toString(campos));

                productService.alter();

                try {

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
                    } else if (productService.searchUPC(Long.parseLong(campos[0])).isPresent()) {
                        Product producto = new Product(Long.parseLong(campos[0]), campos[1], campos[2],
                                Integer.parseInt(campos[3]), Double.parseDouble(campos[4]));

                        productService.update(producto);
                        line = bufferLectura.readLine();
                        counter++;
                    } else {
                        Product producto = new Product(Long.parseLong(campos[0]), campos[1], campos[2],
                                Integer.parseInt(campos[3]), Double.parseDouble(campos[4]));

                        productService.save(producto);

                        System.out.println("se creo el objeto " + campos[1]);
                        line = bufferLectura.readLine();
                        counter++;
                    }
                } catch (NumberFormatException nfe) {
                    logger.info("Falta el codigo PLU o el mismo no es correcto para: "
                            + campos[2] + " en la linea " + counter + " del archivo"/*, nfe*/);
                    line = bufferLectura.readLine();
                    counter++;
                    errorCounter++;
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
    }
}