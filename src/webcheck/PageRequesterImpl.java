package webcheck;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import webcheck.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author sean
 */
public class PageRequesterImpl implements PageRequester ,Runnable{

    private ArrayList<PageGui> listners ;
    private String url;
    private String selector;
    private int nodeNo;
    private Elements elements;
    private Document  page;
    private boolean inprogress;
    
    public PageRequesterImpl(String url, String selector, int nodeNo)
    {
        listners = new ArrayList<>();
        this.url = url;
        this.selector = selector;
        this.nodeNo = nodeNo;
        this.inprogress = false;
        
    }

    PageRequesterImpl() {
        listners = new ArrayList<>();
    }
    
    
    
    
    
    @Override
    public void getRequest(String url) {
        if(url == null)
            url = this.url;
        
        CloseableHttpClient httpClient =  HttpClients.createDefault();
        CloseableHttpResponse response; 
        try {
            URL web = new URL(url);
            HttpGet htppGet = new HttpGet(web.toString());
            response = httpClient.execute(htppGet);
            page = Jsoup.parse(EntityUtils.toString(response.getEntity()));
            response.close();
        }catch(UnknownHostException uhe)
        {
            System.out.println("Could not find host");
        } 
        catch (MalformedURLException ex) {
           System.out.println("Please Check Url eg: http://www.google.com");
        }catch (IOException ex) {
           
        } finally
        {
            
        }
    
    
    }

    public void filter(String selector, int nodeNo) {
        if(selector == null)
            selector = this.selector;
        
        if(nodeNo>0)
        {
            Elements temp = page.select(selector);
            elements = new Elements();
            for(Element e : temp)
                elements.add(e.child(nodeNo));
        }else
           elements = page.select(selector);
    
    }

    @Override
    public void ready() {
        for(PageGui p : listners)
            p.updated(this);
    }

    @Override
    public void addListner(PageGui g) {
        listners.add(g);
    }

    @Override
    public void removeListener(PageGui g) {
        listners.remove(g);
    }

    @Override
    public void setUrl(String url) {
    
        this.url = url;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    public void setSelector(String selector) {
         this.selector = selector;
    }

    @Override
    public String getSelector() {
        return this.selector;
    }

    @Override
    public void setNodeNo(int nodeNo) {
        this.nodeNo = nodeNo;
    }

    @Override
    public int getNodeNo() {
        return this.nodeNo;
    }

    @Override
    public Elements getElements() {
        return this.elements;
    }

    @Override
    public Document getPage() {
        return this.page;
    }

    @Override
    public void run() {
        getRequest(this.url);
        filter(this.selector, this.nodeNo);
        ready();
        this.inprogress =false;
    
    }

    @Override
    public void runRequest() {
    
        if(!this.inprogress)
        {
            this.inprogress = true;
            Thread t = new Thread(this);
            t.run();
        }
    
    }
    
    
    
    
    
}
