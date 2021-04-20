package com.dv16888.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dv16888.www.entity.Ip2locations;

public interface Ip2LocationsRepository extends JpaRepository<Ip2locations, Integer> {
    //"SELECT * FROM chat_ip2locations WHERE start_ip_long < "  + decimal_ip + " AND end_ip_long > " + decimal_ip;
    //  SELECT * FROM chat_ip2locations WHERE start_ip_long < 16793601 AND end_ip_long > 16793601
    public Ip2locations findFirstByStartIpLongLessThanAndEndIpLongGreaterThan( Long decimal_ip, Long decimal_ip2  );
}
