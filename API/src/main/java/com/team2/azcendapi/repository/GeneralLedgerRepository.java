package com.team2.azcendapi.repository;

import com.team2.azcendapi.model.GeneralLedger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralLedgerRepository extends CrudRepository<GeneralLedger,
        Integer> {

    GeneralLedger findByGlId(int glID);

    List<GeneralLedger> findAll();
}
