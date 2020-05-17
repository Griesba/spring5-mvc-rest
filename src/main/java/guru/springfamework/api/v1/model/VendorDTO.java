package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {
    private String id;
    @ApiModelProperty(value = "Vendor first name", required = true)
    private String name;
}
