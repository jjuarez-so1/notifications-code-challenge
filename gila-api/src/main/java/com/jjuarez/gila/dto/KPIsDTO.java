package com.jjuarez.gila.dto;

public class KPIsDTO {
    private KPITypeDTO users;
    private KPITypeDTO smsNotifications;
    private KPITypeDTO emailNotifications;
    private KPITypeDTO pushNotifications;
    private boolean inProgress;

    public KPITypeDTO getUsers() {
        return users;
    }

    public void setUsers(KPITypeDTO users) {
        this.users = users;
    }

    public KPITypeDTO getSmsNotifications() {
        return smsNotifications;
    }

    public void setSmsNotifications(KPITypeDTO smsNotifications) {
        this.smsNotifications = smsNotifications;
    }

    public KPITypeDTO getEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(KPITypeDTO emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public KPITypeDTO getPushNotifications() {
        return pushNotifications;
    }

    public void setPushNotifications(KPITypeDTO pushNotifications) {
        this.pushNotifications = pushNotifications;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }
}
