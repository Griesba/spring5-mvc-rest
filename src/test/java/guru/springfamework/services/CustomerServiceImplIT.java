package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.bootstrap.Bootstrap;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest// this brings up repository in the context for testing
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading Customer Data");

        //setup data for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run(); //load data
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void testPatchFirstName() {
        Long id = getCustomerIdValue();

        CustomerDTO momo = new CustomerDTO();
        momo.setFirstName("Momo");

        CustomerDTO customerDTO = customerService.findById(id);

        assertNotNull(customerDTO);

        customerService.patchCustomer(id, momo);

        CustomerDTO patchedCustomer = customerService.findById(id);
        assertNotNull(patchedCustomer);
        assertEquals(patchedCustomer.getFirstName(), momo.getFirstName());
        assertThat(customerDTO.getFirstName(), not(equalTo(patchedCustomer.getFirstName())));
    }

    @Test
    public void patchCustomerUpdateLastName() throws Exception {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        //save original first/last name
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getLastName());
        assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
        assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
    }

    private Long getCustomerIdValue(){
        List<CustomerDTO> customers = customerService.findAll();

        System.out.println("Customers Found: " + customers.size());

        //return first id
        return customers.get(0).getId();
    }
}
