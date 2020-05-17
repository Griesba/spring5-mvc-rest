package guru.springframework.controllers.v1;

import guru.springframework.controllers.RestResponseEntityExceptionHandler;
import guru.springframework.model.CustomerDTO;
import guru.springframework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestControllerTest {

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;
    @Mock
    private CustomerService customerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testGetAllCustomer() throws Exception {
        CustomerDTO onana = new CustomerDTO();
        onana.setFirstName("Onana");
        CustomerDTO peter = new CustomerDTO();
        peter.setFirstName("peter");

        when(customerService.findAll()).thenReturn(Arrays.asList(onana, peter));

        mockMvc.perform(get("/api/v1/customers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    public void testFindCustomerById() throws Exception {
        CustomerDTO onana = new CustomerDTO();
        onana.setFirstName("Onana");

        when(customerService.findById(anyLong())).thenReturn(onana);

        mockMvc.perform(get("/api/v1/customers/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Onana")));


    }

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerDTO basil = new CustomerDTO();
        basil.setFirstName("Basile");
        basil.setCustomerUrl("/api/v1/customers/");

        when(customerService.createCustomer(ArgumentMatchers.any())).thenReturn(basil);

        mockMvc.perform(post("/api/v1/customers/")
                .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(basil)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Basile")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/")));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        CustomerDTO dodo = new CustomerDTO();
        dodo.setFirstName("dodo");

        when(customerService.updateCustomer(anyLong(), ArgumentMatchers.any(CustomerDTO.class))).thenReturn(dodo);

        mockMvc.perform(put("/api/v1/customers/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(dodo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("dodo")));

    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/v1/customers/" + anyLong())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
