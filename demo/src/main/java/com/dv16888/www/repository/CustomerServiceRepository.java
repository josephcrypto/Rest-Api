package com.dv16888.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dv16888.www.entity.Customerservice;

public interface CustomerServiceRepository extends JpaRepository<Customerservice, Integer> {
}
