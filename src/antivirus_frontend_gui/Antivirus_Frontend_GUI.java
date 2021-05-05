/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * New GUI built with JavaFX
 * Current Version: 0.2
 *
 * This version is not yet functional but shows the completed design and shell
 * of the GUI, next update will add the required buttons and functionality to 
 * plug into the backend
*/

package antivirus_frontend_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

/**
 *
 * @author jacks
 */
public class Antivirus_Frontend_GUI extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("Team-A Antivirus (v0.2 pre-alpha)");
        stage.show();
        
        Image image = new Image("antivirus_frontend_gui/images/icon.png");
        stage.getIcons().add(image);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
