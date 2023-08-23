package com.jjuarez.gila.dto;

public class KPITypeDTO {
    private String title;
    private long quantity;

    public KPITypeDTO(String title, long quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
