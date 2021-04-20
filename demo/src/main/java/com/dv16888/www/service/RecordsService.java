package com.dv16888.www.service;

import com.dv16888.www.entity.Records;
import com.dv16888.www.repository.RecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordsService {
    @Autowired
    private RecordsRepository recordsRepository;

    public List<Records> listAll(){
        return recordsRepository.findAll();
    }
    public void save(Records records) {
        recordsRepository.save(records);
    }
    public Records get(Integer rid) {
        return recordsRepository.findById(rid).get();
    }
    public void delete(Integer rid) {
        recordsRepository.deleteById(rid);
    }
}
