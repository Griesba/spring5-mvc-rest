package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controllers.ResourceNotFoundException;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> findAll() {
        return vendorRepository.findAll().stream()
                .map(vendorMapper::vendorToVendorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        Vendor saveVendor = vendorRepository.save(vendor);
        return vendorMapper.vendorToVendorDTO(saveVendor);
    }

    @Override
    public VendorDTO getVendorById(long id) {
        return vendorRepository.findById(id)
                .map(vendor -> vendorMapper.vendorToVendorDTO(vendor))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO saveVendorByDTO(long anyLong, VendorDTO vendorDTO) {
            Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
            vendor.setId(anyLong);
            return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
    }

    @Override
    public void deleteVendorById(long anyLong) {
        vendorRepository.deleteById(anyLong);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if (vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
            }
            Vendor savedVendor = vendorRepository.save(vendor);
            return vendorMapper.vendorToVendorDTO(savedVendor);
        }).orElseThrow(ResourceNotFoundException::new);
    }
}
