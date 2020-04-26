package com.zcf.attend.dao;


import com.zcf.attend.entity.Attend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendRepository  extends MongoRepository<Attend,String> {

    List<Attend> findAllBySign(Integer sign);
}
