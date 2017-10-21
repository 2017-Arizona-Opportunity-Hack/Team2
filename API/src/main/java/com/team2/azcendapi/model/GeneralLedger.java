package com.team2.azcendapi.model;

import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "general_ledger")
public class GeneralLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int glId;
    private String glDescription;

    public GeneralLedger() {
        super();
    }

    public GeneralLedger(int glId, String glDescription) {
        this.glId = glId;
        this.glDescription = glDescription;
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

    public static String[] getFmpCsvImportFileHeaders() {
        return new String[]{"glId", "glDescription"};
    }

    public static CellProcessor[] getFmpCsvImportFileCellProcessors() {
        return new CellProcessor[]{
                new org.supercsv.cellprocessor.constraint.NotNull(new ParseInt()),
                new org.supercsv.cellprocessor.constraint.NotNull()
        };
    }
}
