import com.tober.inventory.repository.ProductRepository;

public class Test {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        productRepository.addProduct("Test", 20D, 1);
    }
}
