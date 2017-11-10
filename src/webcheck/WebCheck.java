/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcheck;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.hibernate.annotations.Loader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *
 * @author sean
 */
public class WebCheck extends Application{

    /**
     * @param args the command line arguments
     */

    private Stage primaryStage;
    private AnchorPane rootLayout;
   

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WebScraper");

        initRootLayout();

    }

    
    
    
     public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WebCheck.class.getResource("MainApp.fxml"));
            rootLayout = (AnchorPane) loader.load();
            MainAppController control = (MainAppController) loader.getController();
            control.addObserable(new PageRequesterImpl());
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
     
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
         launch(args);
         
        
        /*URIBuilder uriBuild = new URIBuilder();
        uriBuild.setScheme("http");
        uriBuild.setHost("www.bit.lk");
      
        CloseableHttpClient httpClient =  HttpClients.createDefault();
       CloseableHttpResponse reponse;
        try {
             HttpGet htppGet = new HttpGet(uriBuild.build());
          reponse = httpClient.execute(htppGet);
          
            /*System.out.println(reponse.toString());
            System.out.println(EntityUtils.toString(reponse.getEntity()));
            
            HttpEntity enty = reponse.getEntity();*/
          /*  String json  = EntityUtils.toString(reponse.getEntity());
            Document doc = Jsoup.parse(json);
            Elements newsHeadlines = doc.select("");
           
            for (Element headline : newsHeadlines) {
                Element s = headline.child(0);
                System.out.println(s.text());
            }
            
         reponse.close();
        } catch (IOException ex) {
            Logger.getLogger(WebCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(WebCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            
        }*/
       
    }
    
}
