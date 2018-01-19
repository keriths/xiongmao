package com.xm.platform.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;

/**
 * Created by luokaiming on 2018/1/17 0017.
 */
public class JsoupDemo {


    public static void main (String[] args) throws Exception{
        Document document=Jsoup.parse(new File("src/main/webapp/a.xml"),"UTF-8");
        for(Element el:document.select("book")){
            System.out.println("标题："+el.select("title").text()+"  价格： "+el.select("price").text());

        }

    }
}
