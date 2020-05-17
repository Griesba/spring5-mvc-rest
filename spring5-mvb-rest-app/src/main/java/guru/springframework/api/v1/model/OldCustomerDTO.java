package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//now we are using generated class from xjc
@Data
public class OldCustomerDTO {
    @ApiModelProperty()
    private Long id;
    @ApiModelProperty(required = true)
    private String firstName;
    @ApiModelProperty(required = true)
    private String lastName;
    @JsonProperty("customer_url")
    private String customerUrl;
}
