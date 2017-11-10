/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcheck;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author sean
 */
public interface PageRequester {
    
    public void getRequest(String url);
    public void filter(String selector, int nodeNo);
    public void ready();
    public void addListner(PageGui g);
    public void removeListener(PageGui g);
    public void setUrl(String url);
    public String getUrl();
    public void setSelector(String selector );
    public String getSelector();
    public void setNodeNo(int nodeNo);
    public int getNodeNo();
    public Elements getElements();
    public Document getPage();
    public void runRequest();
}
