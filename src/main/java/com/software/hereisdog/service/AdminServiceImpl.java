package com.software.hereisdog.service;

import com.software.hereisdog.dao.mybatis.mapper.AdminMapper;
import com.software.hereisdog.domain.Customer;
import com.software.hereisdog.domain.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return adminMapper.findAllCustomers();
    }

    @Override
    public List<Owner> findAllOwners() {
        return adminMapper.findAllOwners();
    }

    @Override
    public void deleteCustomer(Long id) {
        adminMapper.deleteCustomer(id);
    }

    @Override
    public void deleteOwner(Long id) {
        adminMapper.deleteOwner(id);
    }
}
