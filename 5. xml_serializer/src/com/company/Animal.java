package com.company;

@XmlObject
public class Animal {
    @XmlTag
    private String name = "Homo Sapiens";
    @XmlAttribute(name = "class")
    private String clazz = "primate";
}
