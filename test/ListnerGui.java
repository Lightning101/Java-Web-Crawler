
import java.util.ArrayList;
import webcheck.PageGui;
import webcheck.PageRequester;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sean
 */
public class ListnerGui implements PageGui{

    
    
   private ArrayList<PageRequester> list;
   
   public ListnerGui(PageRequester p)
   {
     
   }

    ListnerGui() {
        list = new ArrayList<>();
    }
    
   
    @Override
    public void updated(PageRequester p) {
        System.out.println(p.getElements().get(0).text());
    }

    @Override
    public void addObserable(PageRequester p) {
        list.add(p);
    }

    @Override
    public void removeObserable(PageRequester p) {
    }
    
}
