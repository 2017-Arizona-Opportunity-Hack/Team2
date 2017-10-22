package com.team2.azcendapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "general_ledger")
public class GeneralLedger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int glId;
    private String glDescription;

    @JsonIgnore
    @OneToMany(mappedBy = "generalLedger")
    private Set<Invoice> invoices = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "bill_type_id")
    private BillType billType;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "gl_job",
            joinColumns = @JoinColumn(name = "generalLedger_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private Set<Job> jobs = new LinkedHashSet<>();

    public GeneralLedger() {
        super();
    }

    public GeneralLedger(int glId, String glDescription) {
        this.glId = glId;
        this.glDescription = glDescription;
    }

    public static String[] getFmpCsvImportFileHeaders() {
        return new String[]{"glId", "glDescription"};
    }

    public static CellProcessor[] getFmpCsvImportFileCellProcessors() {
        return new CellProcessor[]{
                new org.supercsv.cellprocessor.constraint.NotNull(new ParseInt()),
                new org.supercsv.cellprocessor.constraint.NotNull()
        };
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGlId() {
        return glId;
    }

    public void setGlId(int glId) {
        this.glId = glId;
    }

    public String getGlDescription() {
        return glDescription;
    }

    public void setGlDescription(String glDescription) {
        this.glDescription = glDescription;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
}
