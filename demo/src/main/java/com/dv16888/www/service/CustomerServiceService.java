package com.dv16888.www.service;

import com.dv16888.www.entity.Customerservice;
import com.dv16888.www.repository.CustomerServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceService {
    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    public List<Customerservice> listAll(){
        return customerServiceRepository.findAll();
    }
    public void save(Customerservice chatCustomerService) {
        customerServiceRepository.save(chatCustomerService);
    }
    public Customerservice get(Integer csid){
        return customerServiceRepository.findById(csid).get();
    }
    public void delete(Integer csid) {
        customerServiceRepository.deleteById(csid);
    }
}
