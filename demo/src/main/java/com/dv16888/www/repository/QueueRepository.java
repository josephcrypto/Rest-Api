package com.dv16888.www.repository;

import com.dv16888.www.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface QueueRepository  extends JpaRepository<Queue,Integer> {
    //根据customerid从对列表里查询
    public Queue findFirstByCustomerid(String customerid);

    //查询排在之前的人数
    public Long countByActiveAndCreatedatLessThan(Byte active, Date createdat);

    /**
     *   query the list of queue of waiting
     *   SELECT * FROM chat_queue where active=0 order by createdat;
    * */
    public List<Queue> findByActiveOrderByCreatedat(Byte active);
}
