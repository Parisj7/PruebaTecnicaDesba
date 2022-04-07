package Project.PruebaTecnicaFrancoParis.Service;

import Project.PruebaTecnicaFrancoParis.Dao.ProductDao;
import Project.PruebaTecnicaFrancoParis.Model.Product;

import java.util.Optional;

public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void save(Product product){
        productDao.save(product);
    }

//    public void alter(){
//        productDao.alterTable();
//    }

    public Optional<Product> searchUPC(Long upc){
        return productDao.searchUPC(upc);
    }

    public void update (Product product){
        productDao.update(product);
    }
}