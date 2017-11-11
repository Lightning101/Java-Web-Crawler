package webcheck;

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

    
    
   public PageRequester p;
   
   public ListnerGui(PageRequester p)
   {
       this.p = p;
   }
    
   
    @Override
    public void updated(PageRequester p) {
        System.out.println(p.getElements().get(0).text());
    }

    @Override
    public void addObserable(PageRequester p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeObserable(PageRequester p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
