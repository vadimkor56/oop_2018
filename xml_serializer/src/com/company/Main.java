package com.company;

public class Main {
    public static void main(String[] args) {
        Model model = new Model("Apple", 128, "iPhone 7 plus");
        Phone phone = new Phone("89198383005", "MTS", model);
        Person person = new Person("Vadim", "RUS", 19, 'M', phone);

        ObjToXML objToXML = new ObjToXML(person);
        String personXML = null;
        try {
            personXML = objToXML.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(personXML);

    }
}