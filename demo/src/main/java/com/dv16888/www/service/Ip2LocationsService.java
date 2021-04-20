package com.dv16888.www.service;

import com.dv16888.www.entity.Ip2locations;
import com.dv16888.www.repository.Ip2LocationsRepository;
import com.dv16888.www.vo.Ip2location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Ip2LocationsService {
    @Autowired
    private Ip2LocationsRepository ip2LocationsRepository;

    /*public List<Ip2locations> listAll(){
        return ip2LocationsRepository.findAll();
    }
    public void save(Ip2locations chatIp2Locations){
        ip2LocationsRepository.save(chatIp2Locations);
    }
    public Ip2locations get(Integer id) {
        return ip2LocationsRepository.findById(id).get();
    }
    public void delete(Integer id) {
        ip2LocationsRepository.deleteById(id);
    }*/

    //查询地址
    public Ip2location getLocation(Long decimal_ip){
        Ip2locations ip2 = ip2LocationsRepository.findFirstByStartIpLongLessThanAndEndIpLongGreaterThan(decimal_ip, decimal_ip);
        Ip2location location = new Ip2location();

        location.setId( ip2.getId() );
        location.setStart_ip( ip2.getStart_ip() );
        location.setEnd_ip( ip2.getEnd_ip() );
        location.setStartIpLong( ip2.getStartIpLong() );
        location.setEndIpLong( ip2.getEndIpLong() );
        location.setCountry( ip2.getCountry() );
        location.setProvince( ip2.getProvince() );
        location.setCity( ip2.getCity() );
        location.setIsp( ip2.getIsp() );

        return location;
    }
}
