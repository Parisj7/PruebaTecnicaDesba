package Project.PruebaTecnicaFrancoParis.Dao;

import Project.PruebaTecnicaFrancoParis.ConfigurationJDBC;
import Project.PruebaTecnicaFrancoParis.Model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class ProductDao {
    private ConfigurationJDBC configuracionJDBC;

    public ProductDao(ConfigurationJDBC configuracionJDBC) {
        this.configuracionJDBC = configuracionJDBC;
    }

    public void save(Product product) {
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement stmt = null;
        String query = String.format("INSERT INTO Productos(upcplu,marca,descripcion,unidades,precio) VALUES('%s','%s','%s','%s','%s')"
                ,product.getUPCPLU(),product.getMarca(), product.getDescripcion(), product.getUnidades(),product.getPrecio());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            if(keys.next())
                product.setIdProducto(keys.getLong(1));
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Optional<Product> searchUPC(Long upc){
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement stmt = null;
        String query = String.format("SELECT id_producto,upcplu,marca,descripcion,unidades,precio FROM Productos WHERE upcplu= '%s'",upc);
        Product product = null;
        try {
            stmt = connection.createStatement();
            ResultSet result;
            result = stmt.executeQuery(query);
            while (result.next()) {
                product = crearProducto(result);
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product != null ? Optional.of(product) : Optional.empty();
    }

    public void update (Product product){
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement stmt = null;
        String query = String.format("UPDATE products.Productos SET marca='%s', descripcion ='%s', unidades='%s', precio='%s' WHERE upcplu ='%s'"
                ,product.getMarca(), product.getDescripcion(), product.getUnidades(),product.getPrecio(),product.getUPCPLU());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void alterTable(){

        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement stmt = null;
        String queryAlter = String.format("ALTER TABLE `products`.`productos` \n CHANGE COLUMN `id_producto` `id_producto` BIGINT NOT NULL AUTO_INCREMENT ;");
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(queryAlter);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private Product crearProducto(ResultSet result) throws SQLException {
        Long id = result.getLong("id_producto");
        Long upc = result.getLong("upcplu");
        String marca = result.getString("marca");
        String descripcion = result.getString("descripcion");
        Integer unidades = result.getInt("unidades");
        Double precio = result.getDouble("precio");
        return new Product(id, upc, marca, descripcion, unidades, precio);

    }
}