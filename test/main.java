
import org.jsoup.nodes.Element;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sean
 */
public class main {
    
    
    
    public static void main(String[] args) {
       /* ImplRequestNetwork imp = new ImplRequestNetwork("http://www.bit.lk",".field-content",0);
        
        
        
        
        
        imp.getPage("http://www.bit.lk");
        imp.getRequested(".field-content", 0);
        
        for(Element c : imp.elements)
        System.out.println(c.text());
        /*
        imp.sendRequest();
        Elements a = imp.getElements();
        
        while(a == null)
            imp.getElements();
        for(Element e : a)
        {
            e.toString();
        */
       
       PageRequesterImpl p = new PageRequesterImpl("http://www.bit.lk",".field-content",0);
       
       ListnerGui l = new ListnerGui();
       l.addObserable(p);
       p.addListner(l);
       
       p.runRequest();
    }
}
