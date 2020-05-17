package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VendorService {
    List<VendorDTO> findAll();

    VendorDTO createVendor(VendorDTO vendorDTO);

    VendorDTO getVendorById(long id);

    VendorDTO saveVendorByDTO(long anyLong, VendorDTO vendorDTO);

    void deleteVendorById(long anyLong);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);
}
