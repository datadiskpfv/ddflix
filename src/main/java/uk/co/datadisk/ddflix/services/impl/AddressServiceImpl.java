package uk.co.datadisk.ddflix.services.impl;

import org.springframework.stereotype.Service;
import uk.co.datadisk.ddflix.dto.mapper.AddressMapper;
import uk.co.datadisk.ddflix.dto.models.AddressDTO;
import uk.co.datadisk.ddflix.entities.user.Address;
import uk.co.datadisk.ddflix.entities.user.City;
import uk.co.datadisk.ddflix.repositories.user.AddressRepository;
import uk.co.datadisk.ddflix.repositories.user.CityRepository;
import uk.co.datadisk.ddflix.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final CityRepository cityRepository;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper, CityRepository cityRepository) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.cityRepository = cityRepository;
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.getOne(id);
    }

    @Override
    public void removeById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public void saveAddress(AddressDTO addressDTO, Long cityId) {
        City city = cityRepository.findById(cityId).get();
        Address address = addressMapper.AddressDTOToAddress(addressDTO);
        address.setCity(city);
        System.out.println("saveAddress method shipping address ID: " + address.getId() + "and City ID: " + cityId);
        addressRepository.save(address);
    }

    @Override
    public void setDefault(Long addressId, Long userId) {
//        List<Address> addressList = addressRepository.findAllAddressesByUser_Id(userId);
//
//        for (Address address : addressList) {
//            if (address.getId() == addressId) {
//                System.out.println("Default credit card #" + address.getId());
//                //address.setDefaultAddress(true);
//            } else {
//                System.out.println("Remove default credit card #" + address.getId());
//                //address.setDefaultAddress(false);
//            }
//            addressRepository.save(address);
//        }
    }

    @Override
    public int getNumberOfAddresses(Long id) {
        return 1; //addressRepository.findAllAddressesByUser_Id(id).size();
    }

    @Override
    public AddressDTO getAddressDTO(Long id) {
        return addressMapper.AddressToAddressDTO(addressRepository.getOne(id));
    }
}