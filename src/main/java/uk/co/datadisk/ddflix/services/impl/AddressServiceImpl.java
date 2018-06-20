package uk.co.datadisk.ddflix.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.co.datadisk.ddflix.dto.mapper.AddressMapper;
import uk.co.datadisk.ddflix.dto.models.AddressDTO;
import uk.co.datadisk.ddflix.entities.user.Address;
import uk.co.datadisk.ddflix.entities.user.City;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.repositories.user.AddressRepository;
import uk.co.datadisk.ddflix.repositories.user.CityRepository;
import uk.co.datadisk.ddflix.repositories.user.UserRepository;
import uk.co.datadisk.ddflix.services.AddressService;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper, CityRepository cityRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.cityRepository = cityRepository;
        this.userRepository = userRepository;
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
    public void saveAddress(AddressDTO addressDTO, Long cityId, Long userId) {
        City city = cityRepository.findById(cityId).get();
        Address address = addressMapper.AddressDTOToAddress(addressDTO);
        address.setCity(city);
        addressRepository.save(address);
        System.out.println("saveAddress method address ID: " + address.getId() + " and City ID: " + cityId);

        // If this is a new address add to users address list
        if(!addressRepository.findById(address.getId()).isPresent()) {
            User user = userRepository.findById(userId).get();
            user.addAddress(address);
        }

        System.out.println("Debug INFO");
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