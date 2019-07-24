package com.test;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DomTest {

    @Test
    public void parse() throws ParserConfigurationException, IOException, SAXException {
        //1.创建解析工厂类
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        //2.得到解析对象
        DocumentBuilder builder=factory.newDocumentBuilder();
        //3.解析
        Document document=builder.parse("config/scores.xml");
        NodeList list=document.getChildNodes();
        System.out.println(list.item(0).getNodeName());
        //如果是根元素，获取下面的子元素
        if(list.item(0).getNodeName().equals("scores")){
            NodeList scores=list.item(0).getChildNodes();
            for(int i=0;i<scores.getLength();i++){
                Node node=scores.item(i);
                if(node.getNodeName().equals("score")){
                    NodeList scoreList=node.getChildNodes();
                    for(int j=0;j<scoreList.getLength();j++){
                        Node n=scoreList.item(j);
                        String nodename=n.getNodeName();
                        String content=n.getTextContent();
                        if("name".equals(nodename)){
                            System.out.println("姓名："+content);
                        }else if("lesson".equals(nodename)){
                            System.out.println("课程："+content);
                        }else if("grade".equals(nodename)){
                            System.out.println("分数："+content);
                        }
                    }
                }
            }
        }
    }
}
