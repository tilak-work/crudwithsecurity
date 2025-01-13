package com.tilak.crudWithMapping.controllers;

import com.tilak.crudWithMapping.entities.Address;
import com.tilak.crudWithMapping.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    // Create a new Address
    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    // Retrieve an Address by ID
    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    // Retrieve all Addresses
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // Update an Address
    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address updatedAddress) {
        Address existingAddress = addressService.getAddressById(id);
        if (existingAddress != null) {
            existingAddress.setCity(updatedAddress.getCity());
            existingAddress.setState(updatedAddress.getState());
            existingAddress.setCountry(updatedAddress.getCountry());
            return addressService.saveAddress(existingAddress);
        }
        return null; // Or throw an exception
    }

    // Delete an Address
    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable Long id) {
        boolean isDeleted = addressService.deleteAddress(id);
        return isDeleted ? "Address deleted successfully" : "Address not found";
    }
}
