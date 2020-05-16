package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {
    private Long id;
    @ApiModelProperty(value = "Vendor first name", required = true)
    private String name;
}
