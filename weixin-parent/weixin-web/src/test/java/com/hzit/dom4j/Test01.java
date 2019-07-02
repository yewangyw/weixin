package com.hzit.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Test01 {


    /**
     * 获取三个元素
     * @throws DocumentException
     */
    @Test
    public void test01() throws DocumentException {

        //1.加载文件
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/main/resources/book.xml"));

        //2.获取Document对象
        System.out.println(document);

        Element root = document.getRootElement();


        //3.加载元素
        Element titleElent = root.element("title");
        String title = titleElent.getText();
        System.out.println(title);

    }

    /**
     * 获取多个元素
     * @throws DocumentException
     */
    @Test
    public void test02() throws DocumentException {

        //1.加载文件
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/main/resources/books.xml"));

        //2.获取Document对象
        System.out.println(document);

        Element root = document.getRootElement();

        //3.根节点下的所有子节点
        List<Element> elements = root.elements(); // book book
        for (Element element : elements) {

            //获取节点的属性值
            String type = element.attributeValue("type"); //type属性
            System.out.println("书籍的类型:"+type);

            //获取element中的子节点
            List<Element> subList = element.elements();// id title price

            for (Element element1 : subList) {
                String name = element1.getName();
                String text = element1.getText();
                System.out.print("\n name:"+name+" text:"+text);

            }
            System.out.println(); //换行
        }


    }


    /**
     * 操作节点
     */
    @Test
    public void test03() throws DocumentException, IOException {

        //1.加载文件
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/main/resources/books.xml"));

        //2.获取Document对象
        System.out.println(document);
        Element root = document.getRootElement();

        //3.添加节点
        Element addElement = root.addElement("book");
        addElement.addAttribute("type","体育"); //添加属性
        addElement.addElement("title").setText("科比自传");


        //4.移除节点
        Iterator<Element> iterator = root.elementIterator();
        while(iterator.hasNext())
        {
            Element element = iterator.next();

            String type = element.attributeValue("type");

            if("恐怖小说".equals(type))
            {
                root.remove(element);
                break;
            }

            if("编程".equals(type))
            {
                Element element1 = element.element("title");
                element1.setText("java编程思想");
                break;
            }
        }


        //4.输出到指定的文件
        OutputFormat format= OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8"); //指定XML编码
        XMLWriter writer=new XMLWriter(new FileWriter("src/main/resources/books.xml"),format);
        writer.write(document);
        writer.close();

        System.out.println("添加成功");


    }


}
