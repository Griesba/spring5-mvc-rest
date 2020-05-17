package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api("Vendor Controller API")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "List all vendors", notes = "Notes about the vendor API")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public VendorListDTO getAll(){
        return new VendorListDTO(vendorService.findAll());
    }

    @ApiOperation(value = "Create a new vendor", notes = "create vendor with name")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createVendor(VendorDTO vendorDTO){
        return vendorService.createVendor(vendorDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, VendorDTO vendorDTO){
        return vendorService.patchVendor(id, vendorDTO);
    }


}
