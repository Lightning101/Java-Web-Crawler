/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcheck;

/**
 *
 * @author sean
 */
public interface PageGui {
    
    
    public void updated(PageRequester p);
    public void addObserable(PageRequester p);
    public void removeObserable(PageRequester p);
    public void statusReport(PageRequester p);
    
}
