package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createCategories();
        createCustomers();

        System.out.println("Data Loaded = " + categoryRepository.count() );

    }

    private void createCustomers() {
        Customer onana = new Customer();
        onana.setFirstName("Onana");
        onana.setLastName("as2");
        onana.setId(12L);
        customerRepository.save(onana);

        Customer gassame = new Customer();
        gassame.setFirstName("Gassame");
        gassame.setLastName("as3");
        gassame.setId(13L);
        customerRepository.save(gassame);

        Customer mamadou = new Customer();
        mamadou.setFirstName("Mamadou");
        mamadou.setLastName("as4");
        mamadou.setId(14L);
        customerRepository.save(mamadou);
    }

    private void createCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
    }
}
