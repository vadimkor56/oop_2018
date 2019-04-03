package com.company;

@XmlObject
public class Phone {
    @XmlTag
    private String number;
    @XmlAttribute(name = "operator")
    private String provider;

    @XmlTag(name = "lastOwner")
    private Model model;

    Phone(String number, String provider, Model model) {
        this.number = number;
        this.provider = provider;
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public String getProvider() {
        return provider;
    }

    public Model getModel() {
        return model;
    }
}
