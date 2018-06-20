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
    public void removeById(Long addressId, Long userId) {
        User user = userRepository.findById(userId).get();
        user.removeAddress(addressRepository.findById(addressId).get());
        addressRepository.deleteById(addressId);
    }

    @Override
    public void saveAddress(AddressDTO addressDTO, Long cityId, Long userId) {
        City city = cityRepository.findById(cityId).get();
        Address address = addressMapper.AddressDTOToAddress(addressDTO);
        address.setCity(city);

        // If address id is null then its a new address and we need to add to users address list
        if (address.getId() == null){
            addressRepository.save(address);
            System.out.println("New address address ID: " + address.getId() + " and City ID: " + cityId);

            User user = userRepository.findById(userId).get();
            user.addAddress(address);
        } else {
            addressRepository.save(address);
            System.out.println("Update address ID: " + address.getId() + " and City ID: " + cityId);
        }
    }

    @Override
    public void setDefaultAddresses(Long userId, Long shippingAddressId, Long billingAddressId) {
       User user = userRepository.findById(userId).get();
       user.setDefaultShippingAddress(addressRepository.findById(shippingAddressId).get());
       user.setDefaultBillingAddress(addressRepository.findById(billingAddressId).get());
    }

    @Override
    public int getNumberOfAddresses(Long id) {
        return 1; //addressRepository.findAllAddressesByUser_Id(id).size();
    }

    @Override
    public AddressDTO getAddressDTO(Long id) {
        return addressMapper.AddressToAddressDTO(addressRepository.findById(id).get());
    }
}