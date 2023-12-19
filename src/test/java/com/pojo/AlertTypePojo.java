package com.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlertTypePojo {



    @JsonProperty("NoOfNotesProcessed")
    private int NoOfNotesProcessed;

    @JsonProperty("alertName")
    private String alertName;

    @JsonProperty("payorType")
    private String payorType;

    @JsonProperty("date")
    private String date;

    @JsonProperty("providerId")
    private String providerId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("isNotesAdded")
    private String isNotesAdded;

    @JsonProperty("isNotesAvailable")
    private String isNotesAvailable;

    @JsonProperty("payorName")
    private String payorName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("policyNumber")
    private String policyNumber;

    @JsonProperty("adjustorNameandPhoneNumber")
    private String adjustorNameandPhoneNumber;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("dateOfAccidentOrInjury")
    private String dateOfAccidentOrInjury;

    @JsonProperty("claimNumber")
    private String claimNumber;

    @JsonProperty("notes")
    private String notes;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("message")
    private String message;

    @JsonProperty("isUBNotesAvailable")
    private boolean isUBNotesAvailable;

    @JsonProperty("isUBNotesAdded")
    private boolean isUBNotesAdded;

    public int getNoOfNotesProcessed() {
        return NoOfNotesProcessed;
    }

    public void setNoOfNotesProcessed(int noOfNotesProcessed) {
        NoOfNotesProcessed = noOfNotesProcessed;
    }

    public String getAlertName() {
        return alertName;
    }

    public void setAlertName(String alertName) {
        this.alertName = alertName;
    }

    public String getPayorType() {
        return payorType;
    }

    public void setPayorType(String payorType) {
        this.payorType = payorType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsNotesAdded() {
        return isNotesAdded;
    }

    public void setIsNotesAdded(String isNotesAdded) {
        this.isNotesAdded = isNotesAdded;
    }

    public String getIsNotesAvailable() {
        return isNotesAvailable;
    }

    public void setIsNotesAvailable(String isNotesAvailable) {
        this.isNotesAvailable = isNotesAvailable;
    }

    public String getPayorName() {
        return payorName;
    }

    public void setPayorName(String payorName) {
        this.payorName = payorName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getAdjustorNameandPhoneNumber() {
        return adjustorNameandPhoneNumber;
    }

    public void setAdjustorNameandPhoneNumber(String adjustorNameandPhoneNumber) {
        this.adjustorNameandPhoneNumber = adjustorNameandPhoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfAccidentOrInjury() {
        return dateOfAccidentOrInjury;
    }

    public void setDateOfAccidentOrInjury(String dateOfAccidentOrInjury) {
        this.dateOfAccidentOrInjury = dateOfAccidentOrInjury;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isUBNotesAvailable() {
        return isUBNotesAvailable;
    }

    public void setUBNotesAvailable(boolean UBNotesAvailable) {
        isUBNotesAvailable = UBNotesAvailable;
    }

    public boolean isUBNotesAdded() {
        return isUBNotesAdded;
    }

    public void setUBNotesAdded(boolean UBNotesAdded) {
        isUBNotesAdded = UBNotesAdded;
    }
}
