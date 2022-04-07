package Project.PruebaTecnicaFrancoParis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigurationJDBC {                //Conector con la base de datos, los metodos comentados son los necesarios
                                                // para otra configuracion permitiendo que el programa no deba cambiar nada mas
    private String dbUrl;
    private String nombreUsuario;
    private String contrasenaUsuario;
    private Connection connection;

     public ConfigurationJDBC() {
        this.dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=Products;integratedSecurity=false;";
        this.nombreUsuario = "sa";
        this.contrasenaUsuario = "yourStrong(!)Password";
    }
//
//    public ConfigurationJDBC() {
//        this.dbUrl = "jdbc:mysql://localhost:3306/products";
//        this.nombreUsuario = "root";
//        this.contrasenaUsuario = "Nutriarosa7";
//    }

    public Connection conectarConBaseDeDatos() {

        try {
            connection = DriverManager.getConnection(dbUrl, nombreUsuario, contrasenaUsuario);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }

}