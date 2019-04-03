package com.company;

@XmlObject
public class Model {
    @XmlTag
    private String firm;
    @XmlTag
    private Integer storage;
    @XmlAttribute(name = "name")
    private String model;

    Model(String firm, Integer storage, String model) {
        this.firm = firm;
        this.storage = storage;
        this.model = model;
    }

    public String getFirm() {
        return firm;
    }

    public Integer getStorage() {
        return storage;
    }

    public String getModel() {
        return model;
    }
}
