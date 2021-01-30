/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.mybatis;


import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author taigai
 * @version MyTest.java, v 0.1 2021年01月17日 16:38 taigai Exp $
 */
public class MyTest {

    public static void main(String[] args) throws Exception {
        // Build DOM
//        InputStream in = MyTest.class.getResourceAsStream("/config/mybatis-config.xml");
//        SAXReader reader = new SAXReader(in);
//        // Create XPath
//        XPathFactory xpathfactory = XPathFactory.newInstance();
//        XPath xpath = xpathfactory.newXPath();
//
//        // Get root node
//        XPathExpression expr = xpath.compile("//settings");
//        Object result = expr.evaluate(doc, XPathConstants.NODE);
//        System.out.println(result);
        dom4j();

    }


    public static void dom() throws Exception {
        // 创建 DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // 创建 DocumentBuilder
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        // 解析DOM TREE
        InputStream in = MyTest.class.getResourceAsStream("/config/mybatis-config.xml");

        // 操作节点
        // API比较裸，不好操作
    }

    /**
     * mapUnderscoreToCamelCase-->true
     * logImpl-->LOG4J2
     * cacheEnabled-->true
     */
    public static void dom4j() throws Exception {
        // 输入流
        InputStream in = MyTest.class.getResourceAsStream("/config/mybatis-config.xml");
        // 创建SAXReader
        SAXReader reader = new SAXReader();
        // 读取，构建文档
        org.dom4j.Document doc = reader.read(in);

        // 操作节点
        final org.dom4j.Element rootElement = doc.getRootElement();
        org.dom4j.Element settings = rootElement.element("settings");
        List<org.dom4j.Element> elements = settings.elements();
        for (org.dom4j.Element e : elements) {
            Attribute name = e.attribute("name");
            Attribute value = e.attribute("value");
            System.out.println(name.getValue() + "-->" + value.getData());
        }

        // 获取所有setting元素，忽略层级
        List<Node> nodes = rootElement.selectNodes("//setting");
        for (Node node : nodes) {
            System.out.println(node.getName());
        }

        // 获取settings
        nodes = rootElement.selectNodes("settings");
        for (Node node : nodes) {
            System.out.println(node.getName());
        }

        // 获取databaseIdProvider下面的property
        nodes = rootElement.selectNodes("databaseIdProvider/property");
        for (Node node : nodes) {
            if (node instanceof org.dom4j.Element) {
                org.dom4j.Element e = (org.dom4j.Element) node;
                System.out.println(e.attribute("name").getValue());
            }
        }

        // 获取第一个mapper，注意下标从1开始
        nodes = rootElement.selectNodes("mappers/mapper[1]");
        for (Node node : nodes) {
            if (node instanceof org.dom4j.Element) {
                org.dom4j.Element e = (org.dom4j.Element) node;
                System.out.println(e.attribute("resource").getValue());
            }
        }

        // 获取最后一个property，注意下标从1开始
        nodes = rootElement.selectNodes("databaseIdProvider/property[last()]");
        for (Node node : nodes) {
            if (node instanceof org.dom4j.Element) {
                org.dom4j.Element e = (org.dom4j.Element) node;
                System.out.println(e.attribute("name").getValue());
            }
        }

        // 获取倒数第二个property，注意下标从1开始
        nodes = rootElement.selectNodes("databaseIdProvider/property[last()-1]");
        for (Node node : nodes) {
            if (node instanceof org.dom4j.Element) {
                org.dom4j.Element e = (org.dom4j.Element) node;
                System.out.println(e.attribute("name").getValue());
            }
        }

        // 获取所有带有value属性的property
        nodes = rootElement.selectNodes("//property[@value]");
        for (Node node : nodes) {
            if (node instanceof org.dom4j.Element) {
                org.dom4j.Element e = (org.dom4j.Element) node;
                System.out.println(e.attribute("value").getValue());
            }
        }

        // 获取所有property，且其name属性值为driver的元素
        nodes = rootElement.selectNodes("//property[@name='driver']");
        for (Node node : nodes) {
            if (node instanceof org.dom4j.Element) {
                org.dom4j.Element e = (org.dom4j.Element) node;
                System.out.println(e.attribute("value").getValue());
            }
        }

        // 获取所有property，且其name属性值为driver的元素
        nodes = rootElement.selectNodes("//property[@name='driver']");
        for (Node node : nodes) {
            if (node instanceof org.dom4j.Element) {
                org.dom4j.Element e = (org.dom4j.Element) node;
                System.out.println(e.attribute("value").getValue());
            }
        }

        // 更改节点...
//        Element test = rootElement.addElement("test");
//        test.setText("lzy");

        // 写回XML
//        XMLWriter writer = new XMLWriter(new FileOutputStream(new File("out.xml")), new OutputFormat("\t", false));
//        writer.write(doc);
    }
}