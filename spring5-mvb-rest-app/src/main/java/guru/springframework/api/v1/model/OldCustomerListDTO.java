package guru.springframework.api.v1.model;

import guru.springframework.model.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


// now using the generated class from xjc
@Data
@AllArgsConstructor
public class OldCustomerListDTO {
    private List<CustomerDTO> customers;
}
