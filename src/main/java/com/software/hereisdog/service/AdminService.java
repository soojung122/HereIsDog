package com.software.hereisdog.service;

import com.software.hereisdog.domain.Customer;
import com.software.hereisdog.domain.Owner;

import java.util.List;

public interface AdminService {
    List<Customer> findAllCustomers();
    List<Owner> findAllOwners();
    void deleteCustomer(Long id);
    void deleteOwner(Long id);
}
