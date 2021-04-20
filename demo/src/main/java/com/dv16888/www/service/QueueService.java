package com.dv16888.www.service;

import com.dv16888.www.entity.Queue;
import com.dv16888.www.repository.QueueRepository;
//import com.dv16888.www.vo.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QueueService {
    @Autowired
    private QueueRepository queueRepository;

    public List<com.dv16888.www.vo.Queue> listAll(){
        List<com.dv16888.www.vo.Queue> queues = new ArrayList<com.dv16888.www.vo.Queue>();
        List<Queue> lists = queueRepository.findAll();

        for (Queue one : lists){
            com.dv16888.www.vo.Queue aqueue = new com.dv16888.www.vo.Queue();
            aqueue.setActive(one.getActive());
            aqueue.setQid(one.getQid());
            aqueue.setCity(one.getCity());
            aqueue.setCountry(one.getCountry());
            aqueue.setCsid(one.getCsid());
            aqueue.setCustomerid(one.getCustomerid());
            aqueue.setCreatedat(one.getCreatedat());
            aqueue.setIp(one.getIp());
            aqueue.setIsp(one.getIsp());
            aqueue.setProvince(one.getProvince());
            queues.add(aqueue);
        }

        return queues;
    }

    public void save(com.dv16888.www.vo.Queue queue) {
        Queue one = new Queue();

        one.setActive(queue.getActive());
        one.setQid(queue.getQid());
        one.setCity(queue.getCity());
        one.setCountry(queue.getCountry());
        one.setCsid(queue.getCsid());
        one.setCustomerid(queue.getCustomerid());
        one.setCreatedat(queue.getCreatedat());
        one.setIp(queue.getIp());
        one.setIsp(queue.getIsp());
        one.setProvince(queue.getProvince());

        queueRepository.save(one);
    }

    public com.dv16888.www.vo.Queue get(Integer qid) {
        com.dv16888.www.vo.Queue one = new com.dv16888.www.vo.Queue();
        Queue getOne = queueRepository.findById(qid).get();
        one.setActive(getOne.getActive());
        one.setQid(getOne.getQid());
        one.setCity(getOne.getCity());
        one.setCountry(getOne.getCountry());
        one.setCsid(getOne.getCsid());
        one.setCustomerid(getOne.getCustomerid());
        one.setCreatedat(getOne.getCreatedat());
        one.setIp(getOne.getIp());
        one.setIsp(getOne.getIsp());
        one.setProvince(getOne.getProvince());

        return one;
    }

    public void delete(Integer qid) {
        queueRepository.deleteById(qid);
    }

    public com.dv16888.www.vo.Queue findFirstByCustomerid(String customerid) {
        com.dv16888.www.vo.Queue one = new com.dv16888.www.vo.Queue();
        Queue getOne = queueRepository.findFirstByCustomerid(customerid);
        one.setActive(getOne.getActive());
        one.setQid(getOne.getQid());
        one.setCity(getOne.getCity());
        one.setCountry(getOne.getCountry());
        one.setCsid(getOne.getCsid());
        one.setCustomerid(getOne.getCustomerid());
        one.setCreatedat(getOne.getCreatedat());
        one.setIp(getOne.getIp());
        one.setIsp(getOne.getIsp());
        one.setProvince(getOne.getProvince());

        return one;
    }

    //查询之前排队的人数
    public Long showCount(Date createdat) {
        Byte b = 0;
        return queueRepository.countByActiveAndCreatedatLessThan(b, createdat);
    }

    /**
     * show list of queue of waiting
     * */
    public List<com.dv16888.www.vo.Queue> showListQueue() {
        List<com.dv16888.www.vo.Queue> lists = new ArrayList<com.dv16888.www.vo.Queue>();
        Byte b = 0;
        List<Queue> getQueues = queueRepository.findByActiveOrderByCreatedat(b);

        for(Queue one : getQueues) {
            com.dv16888.www.vo.Queue another = new com.dv16888.www.vo.Queue();
            another.setIsp(one.getIsp());
            another.setProvince(one.getProvince());
            another.setCity(one.getCity());
            another.setCountry(one.getCountry());
            another.setCsid(one.getCsid());
            another.setCustomerid(one.getCustomerid());
            another.setIp(one.getIp());
            another.setQid(one.getQid());
            another.setActive(one.getActive());
            another.setCreatedat(one.getCreatedat());
            lists.add(another);
        }

        return lists;
    }
}
