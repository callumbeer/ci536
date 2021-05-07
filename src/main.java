/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author jacks
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * @param arg0
     * @throws Exception
     */
    @Override
    public void start(Stage arg0) throws Exception {
        Antivirus_Frontend_GUI AFG = new Antivirus_Frontend_GUI();
        AFG.start(arg0); 
    }
    
}
