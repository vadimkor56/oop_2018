package com.company;

@XmlObject
public class Person {
    @XmlTag(name = "fullname")
    private final String name;
    @XmlAttribute(tag = "fullname")
    private final String lang;
    private final int age;
    @XmlAttribute
    private final char sex;

    @XmlTag(name = "phone")
    private Phone phone;

    Person(String name, String lang, int age, char sex, Phone phone) {
        this.name = name;
        this.lang = lang;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    @XmlTag
    public int getAge() {
        return age;
    }

    public String getLang() {
        return lang;
    }

    public char getSex() {
        return sex;
    }

    public Phone getPhone() {
        return phone;
    }
}