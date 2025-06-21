package com.software.hereisdog.dao;

import com.software.hereisdog.domain.Customer;
import com.software.hereisdog.domain.Owner;
import java.util.List;

public interface AdminDAO {
    List<Customer> findAllCustomers();
    List<Owner> findAllOwners();
    void deleteCustomer(Long id);
    void deleteOwner(Long id);
}
