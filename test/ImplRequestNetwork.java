
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sean
 */
public class ImplRequestNetwork implements Runnable, RequestNetwork{

    CloseableHttpResponse reponse;
    String url;
    Elements elements;
    Document page;
    String selector;
    int node;
    boolean inProgress;
    
    

   
    
    
    
    public ImplRequestNetwork(String url , String selector, int node)
    {
        this.url = url;
        this.selector = selector;
        this.node = node;
       
    }
    
    
    @Override 
    public void getPage(String url) {
        this.url = url;
      
       
        CloseableHttpClient httpClient =  HttpClients.createDefault();
         
        try {
            URL web = new URL(url);
            HttpGet htppGet = new HttpGet(web.toString());
            reponse = httpClient.execute(htppGet);
            page = Jsoup.parse(EntityUtils.toString(reponse.getEntity()));
            reponse.close();
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

    

    @Override
    public void getRequested(String selector, int node) {
            
        Elements temp  = page.select(selector);
        
        if(node >0){
            elements = new Elements();
            for(Element a : temp)
            {
               elements.add(a.child(node));
            }
            
        }else
        {
            elements = new Elements(temp);
            
        }
        
    
    }

    @Override
    public void run() {
            this.inProgress = true;
            getPage(this.url);
            if(page!= null)
                getRequested(this.selector, this.node);
            
            this.inProgress = false;
            
    }
    
    
    
    public void sendRequest()
    {
        if(!inProgress)
        {
            Thread t = new Thread(this);
            t.start();
            
        }
    }
    
    public void setUrl(String url)
    {
        this.page = null;
        this.url = url;
    }
    
    public String getUrl()
    {
        return this.url;
    }
    

    @Override
    public Elements getElements() {
          return this.elements;
          
    }
    
    public Document getPage()
    {
        return this.page;
    }
    
    public void setSelector(String selector)
    {
        this.selector = selector;
    }
    
    public String getSelector()
    {
        return this.selector;
    }

    
}
