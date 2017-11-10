/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import webcheck.*;
import java.net.URL;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.jsoup.select.Elements;

/**
 *
 * @author sean
 */
public interface RequestNetwork {

   
    public void getPage(String url);
    public void getRequested(String selector, int node);
    public Elements getElements();
    
    
}
