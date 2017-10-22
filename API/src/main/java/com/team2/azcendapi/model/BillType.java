package com.team2.azcendapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bill_type")
public class BillType {

    @OneToMany(mappedBy = "billType")
    private Set<GeneralLedger> generalLedgerSet = new LinkedHashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String billTypeId;
    private String billDescription;

    public BillType() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<GeneralLedger> getGeneralLedgerSet() {
        return generalLedgerSet;
    }

    public void setGeneralLedgerSet(Set<GeneralLedger> generalLedgerSet) {
        this.generalLedgerSet = generalLedgerSet;
    }

    public String getBillDescription() {
        return billDescription;
    }

    public void setBillDescription(String billDescription) {
        this.billDescription = billDescription;
    }

    public String getBillTypeId() {
        return billTypeId;
    }

    public void setBillTypeId(String billTypeId) {
        this.billTypeId = billTypeId;
    }
}
