package com.company;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class ObjToXML {
    private Class clazz;
    private Object object;
    private XmlObject xmlObject;
    private String xmlString;
    private Integer tabCounter;

    private String objectName;
    private HashMap<String, ArrayList<String>> objectTags = new HashMap<>();
    private HashMap<String, String> objectTagValues = new HashMap<>();
    private HashMap<String, String> objectAttributeValues = new HashMap<>();
    private HashMap<String, String> nestedObjects = new HashMap<>();

    ObjToXML(Object object) {
        this.object = object;
        this.clazz = object.getClass();
    }

    private String getXML(Integer i) throws Exception {
        this.tabCounter = i;
        Annotation classAnnotation = this.clazz.getAnnotation(clazz.getClass());
        if (object.getClass().getSuperclass() != null) {
            Class supClass = object.getClass().getSuperclass();
            if (supClass.isAnnotationPresent(XmlObject.class)) {
                Object supClassObj = supClass.newInstance();
                ObjToXML objToXML = new ObjToXML(supClassObj);
                objToXML.getXML(0);
                this.objectTags.putAll(objToXML.getObjectTags());
            }
        }
        if (classAnnotation == xmlObject) {
            this.objectName = object.getClass().getSimpleName().toLowerCase();
            objectTags.put(objectName, new ArrayList<>());
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(XmlTag.class)) {
                    field.setAccessible(true);
                    XmlTag xmlTag = field.getAnnotation(XmlTag.class);
                    String currentTag = "";
                    String fieldName = field.getName();
                    if (field.get(object).getClass().isAnnotationPresent(XmlObject.class)) {
                        if (!xmlTag.name().equals("")) {
                            currentTag = xmlTag.name();
                        } else {
                            currentTag = field.getName();
                        }
                        ObjToXML objToXML = new ObjToXML(field.get(object));
                        String personXML = null;
                        try {
                            personXML = objToXML.getXML(i + 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        nestedObjects.put(currentTag, personXML);


                    }

                    if (!xmlTag.name().equals("")) {
                        currentTag = xmlTag.name();
                        fieldName = field.getName();
                    } else {
                        currentTag = field.getName();
                        fieldName = field.getName();
                    }

                    objectTags.put(currentTag, new ArrayList<>());
                    try {
                        Field currentField = object.getClass().getDeclaredField(fieldName);
                        currentField.setAccessible(true);
                        String tagValue = "";
                        try {
                            tagValue = currentField.get(object).toString();
                        } catch (Exception e) {
                            System.out.println("Тип тега/аттрибута должен приводиться к String");
                        }
                        objectTagValues.put(currentTag, tagValue);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                } else if (field.isAnnotationPresent(XmlAttribute.class)) {
                    XmlAttribute xmlAttribute = field.getAnnotation(XmlAttribute.class);
                    String currentTag = "";
                    if (!xmlAttribute.tag().equals("")) {
                        currentTag = xmlAttribute.tag();
                    } else {
                        currentTag = objectName;
                    }
                    String currentAttribute = "";
                    if (!xmlAttribute.name().equals("")) {
                        currentAttribute = xmlAttribute.name();
                        objectTags.get(currentTag).add(currentAttribute);
                    } else {
                        currentAttribute = field.getName();
                        objectTags.get(currentTag).add(currentAttribute);
                    }
                    try {
                        Field currentField = object.getClass().getDeclaredField(field.getName());
                        currentField.setAccessible(true);
                        String attributeValue = "";
                        try {
                            attributeValue = currentField.get(object).toString();
                        } catch (Exception e) {
                            System.out.println("Тип тега/аттрибута должен приводиться к String");
                        }
                        objectAttributeValues.put(currentAttribute, attributeValue);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }


            }

            for (Method method : object.getClass().getMethods()) {
                if (method.isAnnotationPresent(XmlTag.class)) {
                    XmlTag xmlTag = method.getAnnotation(XmlTag.class);
                    String currentTag = "";
                    if (method.invoke(object).getClass().isAnnotationPresent(XmlObject.class)) {
                        if (!xmlTag.name().equals("")) {
                            currentTag = xmlTag.name();
                        } else {
                            currentTag = method.getName();
                        }
                        method.setAccessible(true);
                        ObjToXML objToXML = new ObjToXML(method.invoke(object));
                        String personXML = null;
                        try {
                            personXML = objToXML.getXML(i + 1);
                            System.out.println(personXML);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        nestedObjects.put(currentTag, personXML);
                    }

                    if (method.getParameterCount() != 0)
                        throw new Exception("Annotated method has parameters");
                    if (method.getReturnType().equals(Void.TYPE))
                        throw new Exception("Annotated method returns void");
                    if (!xmlTag.name().equals("")) {
                        currentTag = xmlTag.name();
                    } else {
                        String currentTagName = method.getName();
                        if (currentTagName.contains("get"))
                            currentTag = currentTagName.substring(3).toLowerCase();
                        else
                            currentTag = currentTagName;
                    }
                    if (objectTags.containsKey(currentTag))
                        throw new Exception("Same tags");
                    objectTags.put(currentTag, new ArrayList<>());

                    String tagValue = "";
                    try {
                        tagValue = method.invoke(object).toString();
                    } catch (Exception e) {
                        System.out.println("Тип тега/аттрибута должен приводиться к String");
                    }
                    objectTagValues.put(currentTag, tagValue);

                } else if (method.isAnnotationPresent(XmlAttribute.class)) {
                    if (method.getParameterCount() != 0)
                        throw new Exception("Annotated method has parameters");
                    if (method.getReturnType().equals(Void.TYPE))
                        throw new Exception("Annotated method returns void");
                    XmlAttribute xmlAttribute = method.getAnnotation(XmlAttribute.class);
                    String currentTag = xmlAttribute.tag();
                    String currentAttribute = "";
                    if (!xmlAttribute.name().equals("")) {
                        String currentAttributeName = xmlAttribute.name();
                        if (currentAttributeName.contains("get"))
                            currentAttribute = currentAttributeName.substring(3).toLowerCase();
                        else
                            currentAttribute = currentAttributeName;
                        if (objectTags.get(currentTag).contains(currentAttribute))
                            throw new Exception("Same attributes");
                        objectTags.get(currentTag).add(currentAttribute);
                    } else {
                        currentAttribute = method.getName();
                        if (objectTags.get(currentTag).contains(currentAttribute))
                            throw new Exception("Same attributes");
                        objectTags.get(currentTag).add(currentAttribute);
                    }

                    String attributeValue = "";
                    try {
                        attributeValue = method.invoke(object).toString();
                    } catch (Exception e) {
                        System.out.println("Тип тега/аттрибута должен приводиться к String");
                    }
                    objectAttributeValues.put(currentAttribute, attributeValue);

                }
            }
        }
        return this.parseMaps();
    }

    private String parseMaps() {
        StringBuilder xmlStr = new StringBuilder();
        for (Integer i = 0; i < this.tabCounter; ++i) {
            xmlStr.append("\t");
        }
        xmlStr = new StringBuilder("<" + objectName + " ");

        for (String attribute : objectTags.get(objectName)) {
            xmlStr.append(attribute).append("=");
            xmlStr.append("\"").append(objectAttributeValues.get(attribute)).append("\" ");
        }
        xmlStr = new StringBuilder(xmlStr.substring(0, xmlStr.length() - 1));
        xmlStr.append(">\n");

        for (Integer i = 0; i <= this.tabCounter; ++i) {
            xmlStr.append("\t");
        }

        for (String tag : objectTags.keySet()) {
            if (!tag.equals(objectName)) {
                if (nestedObjects.containsKey(tag)) {
                    xmlStr.append(nestedObjects.get(tag));
                } else {
                    xmlStr.append("<").append(tag).append(" ");
                    for (String attribute : objectTags.get(tag)) {
                        xmlStr.append(attribute).append("=");
                        xmlStr.append("\"").append(objectAttributeValues.get(attribute)).append("\" ");
                    }
                    xmlStr = new StringBuilder(xmlStr.substring(0, xmlStr.length() - 1));
                    xmlStr.append(">").append(objectTagValues.get(tag)).append("</").append(tag).append(">\n");

                    for (Integer i = 0; i <= this.tabCounter; ++i) {
                        xmlStr.append("\t");
                    }
                }
            }
        }
        xmlStr = new StringBuilder(xmlStr.substring(0, xmlStr.length() - 1));
        xmlStr.append("</").append(objectName).append(">\n");
        for (Integer i = 0; i < this.tabCounter; ++i) {
            xmlStr.append("\t");
        }

        this.xmlString = xmlStr.toString();
        return xmlStr.toString();
    }

    public String get() throws Exception {
        return this.getXML(0);
    }

    private HashMap<String, ArrayList<String>> getObjectTags() {
        return objectTags;
    }
}
