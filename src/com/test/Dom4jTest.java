package com.test;

import com.entity.Score;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dom4jTest {

    @Test
    public void delete() throws DocumentException, IOException {
        Document document=getDocument();
        Element root=document.getRootElement();
        List scores=root.elements("score");
        for(Object obj:scores){
            Element element=(Element)obj;
            if("2".equals(element.attributeValue("id"))){
                element.getParent().remove(element);
                //root.remove(element);
            }
        }
        generateDocument(document);
    }

    @Test
    public void add() throws DocumentException,
            IOException {
        org.dom4j.Document document = getDocument();
        Element sco= DocumentHelper.createElement("score");
        Element name=DocumentHelper.createElement("name");
        name.add(Namespace.get("阿鑫"));
        Element lesson=DocumentHelper.createElement("lesson");
        lesson.add(Namespace.get("MySql"));
        Element grade=DocumentHelper.createElement("grade");
        grade.add(Namespace.get("98"));
        sco.addAttribute("id","3");
        Element root=document.getRootElement();
        root.add(sco);
        generateDocument(document);
    }

    private void generateDocument(org.dom4j.Document document)
            throws IOException {
        OutputFormat format=OutputFormat.createPrettyPrint();
        //设置文档编码方式
        format.setEncoding("UTF-8");
        //树更新了，把更新的内容写入到xml文档中
        XMLWriter writer=new XMLWriter(new OutputStreamWriter(
                new FileOutputStream("config/scores.xml"),"UTF-8"));
        writer.write(document);
        writer.close();
    }

    @Test
    public void read() throws DocumentException {
        //构造解析器
        org.dom4j.Document document = getDocument();
        //获取根节点
        Element root=document.getRootElement();
        Iterator<Element> iterator=root.elementIterator("score");
        List<Score> scores=new ArrayList<>();
        while (iterator.hasNext()){
            Element sco=iterator.next();
            Score score=new Score();
            score.setId(Integer.parseInt(sco.attributeValue("id")));
            score.setName(sco.elementText("name"));
            score.setLesson(sco.elementText("lesson"));
            score.setGrade(Integer.parseInt(sco.attributeValue("grade")));
            scores.add(score);
        }
        System.out.println(scores);
    }

    private org.dom4j.Document getDocument() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(new File("config/scores.xml"));
    }
}
