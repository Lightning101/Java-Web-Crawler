package webcheck;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
import org.jsoup.select.Selector;

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
    public byte status;
    private enum error
    {
        URL(1),
        CONNECTION(2),
        NODENO(3),
        FILTER(4);
        
        int val;
        error(int i)
        {
            this.val = i;
        }
    }
    
    public PageRequesterImpl(String url, String selector, int nodeNo)
    {
        listners = new ArrayList<>();
        this.url = url;
        this.selector = selector;
        this.nodeNo = nodeNo;
        this.inprogress = false;
        this.status = 0;
        
    }

    PageRequesterImpl() {
        listners = new ArrayList<>();
        this.status = 0;
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
            status |= error.CONNECTION.val;
        } 
        catch (MalformedURLException ex) {
           status |= error.URL.val;
           System.out.println("Please Check Url eg: http://www.google.com");
        }catch (IOException ex) {
           
        } finally
        {
            
        }
    
    
    }

    public void filter(String selector, int nodeNo) {
        if(selector == null)
            selector = this.selector;
        try{
        if(nodeNo>0)
        {
            try{
            Elements temp = page.select(selector);
            elements = new Elements();
            for(Element e : temp)
                elements.add(e.child(nodeNo));
            }catch(IndexOutOfBoundsException ex)
            {
                status |= error.NODENO.val;
            }finally
            {
                elements = page.select(selector);
            }
        }else
           elements = page.select(selector);
        }catch(Selector.SelectorParseException ex)
        {
            status |= error.FILTER.val;
        }
    }

    @Override
    public void ready() {
        if(status == 0){
        for(PageGui p : listners)
            p.updated(this);
        }else
        {
        for(PageGui p : listners)
            p.statusReport(this);
            
        }
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
        if(status == 0)
        filter(this.selector, this.nodeNo);
        ready();
        this.inprogress =false;
    
    }

    @Override
    public void runRequest() {
    
        if(!this.inprogress)
        {
            status = 0;
            this.inprogress = true;
            Thread t = new Thread(this);
            t.run();
        }
    
    }

    @Override
    public byte getStatus() {
        return this.status;
    }
    
    
    
    
    
}
