package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> findAll();

    CustomerDTO findById(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(long anyLong, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
}
