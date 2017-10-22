package com.team2.azcendapi.repository;

import com.team2.azcendapi.model.BillType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTypeRepository extends CrudRepository<BillType,Integer>{

    BillType findByBillTypeId(String id);
}
