package com.tilak.crudWithMapping.services;

import com.tilak.crudWithMapping.entities.Address;
import com.tilak.crudWithMapping.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    // Create or update an Address
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    // Retrieve an Address by ID
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    // Retrieve all Addresses
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Delete an Address by ID
    public boolean deleteAddress(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
