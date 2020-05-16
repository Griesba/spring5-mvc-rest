package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VendorController.VENDORS_URL)
public class VendorController {

    public static final String VENDORS_URL = "/api/v1/vendors";
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public VendorListDTO getAll(){
        return new VendorListDTO(vendorService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createVendor(VendorDTO vendorDTO){
        return vendorService.createVendor(vendorDTO);
    }


}
