package com.example.test;

import java.util.Date;


    public class FactoryModel {

    private Long id;
    private String name;
    private Date foundation;
    private Integer amountWorker;

    public FactoryModel(Long id, String name, Date foundation, Integer amountWorker) {
        this.id = id;
        this.name = name;
        this.foundation = foundation;
        this.amountWorker = amountWorker;
    }

        public FactoryModel() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getFoundation() {
            return foundation;
        }

        public void setFoundation(Date foundation) {
            this.foundation = foundation;
        }

        public Integer getAmountWorker() {
            return amountWorker;
        }

        public void setAmountWorker(Integer amountWorker) {
            this.amountWorker = amountWorker;
        }
    }
