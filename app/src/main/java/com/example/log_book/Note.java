package com.example.log_book;

public class Note {
    private String NAME,MEDICINE,SYMPTOM,CHARGES,DATE,AGE;
    private long ID;
    Note(){}
    Note(String NAME,String MEDICINE,String SYMPTOM,String CHARGES,String DATE,String AGE){
        this.NAME=NAME;
        this.CHARGES=CHARGES;
        this.DATE=DATE;
        this.MEDICINE=MEDICINE;
        this.SYMPTOM=SYMPTOM;
        this.AGE=AGE;
    }
    Note(long id,String NAME,String MEDICINE,String SYMPTOM,String CHARGES,String DATE,String AGE){
       this.ID=id;
        this.NAME=NAME;
        this.CHARGES=CHARGES;
        this.DATE=DATE;
        this.MEDICINE=MEDICINE;
        this.SYMPTOM=SYMPTOM;
        this.AGE=AGE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getMEDICINE() {
        return MEDICINE;
    }

    public void setMEDICINE(String MEDICINE) {
        this.MEDICINE = MEDICINE;
    }

    public String getSYMPTOM() {
        return SYMPTOM;
    }

    public void setSYMPTOM(String SYMPTOM) {
        this.SYMPTOM = SYMPTOM;
    }

    public String getCHARGES() {
        return CHARGES;
    }

    public void setCHARGES(String CHARGES) {
        this.CHARGES = CHARGES;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getAGE() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE= AGE;
    }
}
