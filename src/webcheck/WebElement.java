/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcheck;

import org.jsoup.nodes.Element;




/**
 *
 * @author sean
 */
public class WebElement {
    
    
    public String data;
    public Element element;
    
    
    public WebElement(Element element,String data)
    {
        this.element = element;
        this.data = data;
       
       
        
    }
    
    public void setData(String data)
    {
        this.data = data;
    }
    
    public String getData()
    {
        return this.data;
    }
    
}
