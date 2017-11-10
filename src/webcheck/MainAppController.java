/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcheck;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * FXML Controller class
 *
 * @author sean
 */
public class MainAppController implements Initializable ,PageGui{

    /**
     * Initializes the controller class.
     */
    
    
    @FXML private TextField urlView;
    @FXML private TextField selectorView;
    @FXML private TextField nodeNoView;
    @FXML private ProgressBar progressView;
    @FXML private TableView<WebElement> tableView;
    @FXML private Button buttonView;
    @FXML private TableColumn<WebElement,String> ItemView;
    
    private ArrayList<PageRequester> obserList;
    private ObservableList<WebElement> masterData = FXCollections.observableArrayList();
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obserList = new ArrayList<>();
        
        buttonView.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
               for(PageRequester p : obserList)
               {
                   p.setUrl(urlView.getText());
                   p.setSelector(selectorView.getText());
                   p.setNodeNo(Integer.parseInt(nodeNoView.getText()));
                   p.runRequest();
               }
                
            }
            
        });
    }
    



  

    @Override
    public void updated(PageRequester p) {
    
      Elements el =   p.getElements();
      masterData.clear();
      for(int i = 0; i<el.size(); i++)
      {
          masterData.add(new WebElement(el.get(i),el.get(i).text()));
          
      }
      
      ItemView.setCellValueFactory(new PropertyValueFactory<WebElement,String>("data"));
       tableView.setItems(masterData);
         
             
    }

    @Override
    public void addObserable(PageRequester p) {
        p.addListner(this);
        obserList.add(p);
    }

    @Override
    public void removeObserable(PageRequester p) {
        p.removeListener(this);
        obserList.remove(p);
    }
    
}
