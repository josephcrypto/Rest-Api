package com.dv16888.www.service;

import com.dv16888.www.vo.Visit;
import com.dv16888.www.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;

    public List<Visit> listAll(){
        List<com.dv16888.www.entity.Visit> alldatas = visitRepository.findAll();
        List<Visit> visits = new ArrayList<Visit>();
        for (com.dv16888.www.entity.Visit one : alldatas){
            Visit oneV = new Visit();
            oneV.setContent(one.getContent());
            oneV.setCreated_at(one.getCreated_at());
            oneV.setCustomerid(one.getCustomerid());
            visits.add(oneV);
        }
        return visits;
    }

    public void save(Visit visit) {
        com.dv16888.www.entity.Visit one = new com.dv16888.www.entity.Visit();

        one.setContent(visit.getContent());
        one.setCustomerid(visit.getCustomerid());
        one.setCreated_at(visit.getCreated_at());

        visitRepository.save(one);
    }

}
