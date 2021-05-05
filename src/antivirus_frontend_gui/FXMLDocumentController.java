/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antivirus_frontend_gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author jacks
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ImageView btn_close,btn_minimise;
    
    @FXML
    private AnchorPane anchorpane,scan,logs,settings,accessibility;
    
    @FXML
    private Label btn_scan,btn_scan_current,btn_logs,btn_logs_current,
            btn_settings,btn_settings_current,btn_accessibility,
            btn_accessibility_current;
    
    @FXML
    private void switchTab(MouseEvent event) {
        if (event.getSource() == btn_scan){
            btn_scan.setVisible(false);
            btn_logs.setVisible(true);
            btn_settings.setVisible(true);
            btn_accessibility.setVisible(true);
            
            btn_scan_current.setVisible(true);
            btn_logs_current.setVisible(false);
            btn_settings_current.setVisible(false);
            btn_accessibility_current.setVisible(false);
            
            scan.setVisible(true);
            logs.setVisible(false);
            settings.setVisible(false);
            accessibility.setVisible(false);
            
        }
        else if (event.getSource() == btn_logs){
            btn_scan.setVisible(true);
            btn_logs.setVisible(false);
            btn_settings.setVisible(true);
            btn_accessibility.setVisible(true);
            
            btn_scan_current.setVisible(false);
            btn_logs_current.setVisible(true);
            btn_settings_current.setVisible(false);
            btn_accessibility_current.setVisible(false);
            
            scan.setVisible(false);
            logs.setVisible(true);
            settings.setVisible(false);
            accessibility.setVisible(false);
        }
        else if (event.getSource() == btn_settings){
            btn_scan.setVisible(true);
            btn_logs.setVisible(true);
            btn_settings.setVisible(false);
            btn_accessibility.setVisible(true);
            
            btn_scan_current.setVisible(false);
            btn_logs_current.setVisible(false);
            btn_settings_current.setVisible(true);
            btn_accessibility_current.setVisible(false);
            
            scan.setVisible(false);
            logs.setVisible(false);
            settings.setVisible(true);
            accessibility.setVisible(false);
        }
        else if (event.getSource() == btn_accessibility){
            btn_scan.setVisible(true);
            btn_logs.setVisible(true);
            btn_settings.setVisible(true);
            btn_accessibility.setVisible(false);
            
            btn_scan_current.setVisible(false);
            btn_logs_current.setVisible(false);
            btn_settings_current.setVisible(false);
            btn_accessibility_current.setVisible(true);
            
            scan.setVisible(false);
            logs.setVisible(false);
            settings.setVisible(false);
            accessibility.setVisible(true);
        }
    }
    
    @FXML
    private void handleButtonAction(MouseEvent event) {
        if(event.getTarget() == btn_close){
            System.exit(0);
        }
        else if(event.getTarget() == btn_minimise){
            Stage stage = (Stage)anchorpane.getScene().getWindow();
            stage.setIconified(true);
        }
    }
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    public void windowPress(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }
    
    @FXML
    public void windowDrag(MouseEvent event) {
        Stage stage = (Stage)anchorpane.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }
    
    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
