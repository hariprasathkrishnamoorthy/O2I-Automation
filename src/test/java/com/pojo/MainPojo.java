package com.pojo;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class MainPojo {


    @JsonIgnore()
    private String isRepeatedAlert;
    @JsonIgnore
    private String transactionStartTime;
    @JsonIgnore()
    private String id;
    @JsonIgnore
    private String tenant_id;
    @JsonIgnore
    private String modelName;
    @JsonIgnore
    private String created_by;
    @JsonIgnore
    private String updated_by;
    @JsonIgnore
    private String created_at;
    @JsonIgnore
    private String updated_at;

    public List<AlertTypePojo> alertType;
    private String notes;
    private String status;
    private String alertArrivedDate;
    private String noOfAlert;
    private String queueAdddedTime;
    private String stage;
    private String arrivedTime;
    private String retryCountLimit;
    public int retryCount;
    private String patientName;
    private String mrno;
    private String task;



    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setMrno(String mrno) {
        this.mrno = mrno;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setAlertType(List<AlertTypePojo> alertType) {
        this.alertType = alertType;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAlertArrivedDate(String alertArrivedDate) {
        this.alertArrivedDate = alertArrivedDate;
    }

    public void setNoOfAlert(String noOfAlert) {
        this.noOfAlert = noOfAlert;
    }

    public void setQueueAdddedTime(String queueAdddedTime) {
        this.queueAdddedTime = queueAdddedTime;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public void setArrivedTime(String arrivedTime) {
        this.arrivedTime = arrivedTime;
    }

    public void setRetryCountLimit(String retryCountLimit) {
        this.retryCountLimit = retryCountLimit;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
    public String getIsRepeatedAlert() {
        return isRepeatedAlert;
    }

    public void setIsRepeatedAlert(String isRepeatedAlert) {
        this.isRepeatedAlert = isRepeatedAlert;
    }

    public String getTransactionStartTime() {
        return transactionStartTime;
    }

    public void setTransactionStartTime(String transactionStartTime) {
        this.transactionStartTime = transactionStartTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }



    public String getPatientName() {
        return patientName;
    }
    public List<AlertTypePojo> getAlertType() {
        return alertType;
    }

    public String getMrno() {
        return mrno;
    }

    public String getTask() {
        return task;
    }

    public String getNotes() {
        return notes;
    }

    public String getStatus() {
        return status;
    }

    public String getAlertArrivedDate() {
        return alertArrivedDate;
    }

    public String getNoOfAlert() {
        return noOfAlert;
    }

    public String getQueueAdddedTime() {
        return queueAdddedTime;
    }

    public String getStage() {
        return stage;
    }

    public String getArrivedTime() {
        return arrivedTime;
    }

    public String getRetryCountLimit() {
        return retryCountLimit;
    }

    public int getRetryCount() {
        return retryCount;
    }



}
