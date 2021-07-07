package br.com.adopt.entity;

public enum EnumStatus {

    ACTIVE(0), INACTIVE(1);

    private Integer status;

    EnumStatus(Integer status){
        this.status = status;
    }
    public Integer getType() {
        return status;
    }

}
