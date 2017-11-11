/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcheck;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private static String arg1;
    private static String arg2;
    private static String arg3;
    private static boolean argSet;
    

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
            
            if(argSet ==true)
             {
                 control.setUrl(arg1);
                 control.setSelector(arg2);
                 control.setNodeNo(Integer.parseInt(arg3));
                 control.runPrecdure();
             }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
     
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        if(args.length == 3)
        {
            arg1 = args[0];
            arg2 = args[1];
            arg3 = args[2];
            argSet = true;
        }
         launch(args);
         
        
    }
    
}
