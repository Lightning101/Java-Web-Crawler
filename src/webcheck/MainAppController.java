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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
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
               runPrecdure();
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
    
    
    public void setUrl(String url)
    {
        this.urlView.setText(url);
    }
    
    public void setSelector(String selector)
    {
        this.selectorView.setText(selector);
    }
    
    public void setNodeNo(int nodeNo)
    {
        this.nodeNoView.setText(String.valueOf(nodeNo));
    }
    
    
    public void runPrecdure()
    {
        for(PageRequester p : obserList)
               {
                  try{ p.setUrl(urlView.getText());
                   p.setSelector(selectorView.getText());
                   int i = Integer.parseInt(nodeNoView.getText());
                   if(i<0)
                       throw new NumberFormatException();
                   p.setNodeNo(i);
                   p.runRequest();
                  }catch(NumberFormatException ex)
                  {
                      Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setTitle("EXCEPTION");
                        a.setHeaderText("NODE NO ERROR");
                        a.setContentText("NODE NO: SHOULD CONTAIN A INTEGER EQUAL TO 0 OR GREATER");
                        a.getDialogPane().setExpanded(true);
                        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        a.show();
                  }
               }
    }

    @Override
    public void statusReport(PageRequester p) {
          byte b = p.getStatus();
          if(b == 1)
          {
              Alert a = new Alert(Alert.AlertType.ERROR);
              a.setTitle("EXCEPTION");
              a.setHeaderText("URL FORMATE ERROR");
              a.setContentText("SHOULD BE LIKE: http://www.google.com");
              a.getDialogPane().setExpanded(true);
              a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
              a.show();
          }
          else if(b == 2)
          {
              Alert a = new Alert(Alert.AlertType.ERROR);
              a.setTitle("EXCEPTION");
              a.setHeaderText("CONNECTION ERROR");
              a.setContentText("CONNECTION ISSUE PLEASE RECONNECT AND TRY AGAIN");
              a.getDialogPane().setExpanded(true);
              a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
              a.show();
          }
          else if(b == 3)
          {
              Alert a = new Alert(Alert.AlertType.ERROR);
              a.setTitle("EXCEPTION");
              a.setHeaderText("NODE NO ERROR");
              a.setContentText("NODE VALUE INCORRECT PLEASE REDUCE");
              a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
              a.getDialogPane().setExpanded(true);
              a.show();
          }else if(b == 4)
          {
              Alert a = new Alert(Alert.AlertType.ERROR);
              a.setTitle("EXCEPTION");
              a.setHeaderText("SELECTOR ERROR");
              a.setContentText("PLEASE USE CSS SELECTORS FROM https://www.w3schools.com/cssref/css_selectors.asp");
              a.getDialogPane().setExpanded(true);
              a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
              a.show();
              
          }
          
    }
}
