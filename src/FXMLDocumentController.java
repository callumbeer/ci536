/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.AWTException;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author jacks
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ImageView btn_close,btn_minimise;
    
    @FXML
    private AnchorPane anchorpane,scan,logs,settings,accessibility,csnews,topbar
            ,infopane,windowcontrol;
    
    @FXML
    private Label btn_scan,btn_scan_current,btn_logs,btn_logs_current,
            btn_settings,btn_settings_current,btn_accessibility,
            btn_accessibility_current,btn_start_scan, lastscan, btn_opn_log,
            btn_del_log, btn_autosave, btn_show_news, btn_themeswitch, version,
            copyright, lastupdate, CSNlabel, intro, as, csn, theem, prefiles,
            btn_prefiles;
    
    @FXML
    private WebView newspane;
    
    @FXML
    private ListView<String> loglist;
    
    ObservableList<File> list = FXCollections.observableArrayList();
    ObservableList<String> nameList = FXCollections.observableArrayList();
    
    private void loadLogs() {
        list.removeAll(list);
        nameList.removeAll(nameList);
        
        FileSystemView view = FileSystemView.getFileSystemView();
        list.addAll(view.getFiles(Antivirus_Frontend_GUI.bpath, true));
        
        list.forEach(file ->{
            try {
                String stringed = file.toString();
                System.out.println(file);
                String nameOnly = stringed.substring(43,71);
                nameOnly = nameOnly.replace("_", " ");
                nameOnly = nameOnly.replace(".", "/");
                nameOnly = nameOnly.replace("SCAN LOG", "Scan Log");
                nameOnly = nameOnly + " " + selDir(stringed);
                nameList.add(nameOnly);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        loglist.setItems(nameList);
    }
    
    @FXML
    private void toggleHover(MouseEvent event){
        if(event.getSource() == btn_autosave){
            btn_autosave.setStyle("-fx-text-fill: linear-gradient(#fb6bab, #ee2e7e)");
        }
        else if(event.getSource() == btn_show_news){
            btn_show_news.setStyle("-fx-text-fill: linear-gradient(#fb6bab, #ee2e7e)");
        }
        else if(event.getSource() == btn_themeswitch){
            if(darktheme == false){
                //Do nothing
            }
            else{
                btn_themeswitch.setStyle("-fx-text-fill: linear-gradient(#bcbcbc, #3D4956, 0.7106796116504854, 1.0)");
            }
        }
        else if(event.getSource() == btn_prefiles){
            btn_prefiles.setStyle("-fx-text-fill: linear-gradient(#fb6bab, #ee2e7e)");
        }
    }
    
    @FXML
    private void toggleUnHover(MouseEvent event){
        if(event.getSource() == btn_autosave){
            if(asButtonDown == false){
                btn_autosave.setStyle("-fx-text-fill: #bcbcbc");
                if(darktheme == false){
                    btn_autosave.setStyle("-fx-text-fill: #3D4956");
                }
            }
            else {
                btn_autosave.setStyle("-fx-text-fill: linear-gradient(#fb6bab, #ee2e7e, 0.3106796116504854, 1.0)");
            }
        }
        else if(event.getSource() == btn_show_news){
            if(csnButtonDown == false){
                btn_show_news.setStyle("-fx-text-fill: #bcbcbc");
                if(darktheme == false){
                    btn_show_news.setStyle("-fx-text-fill: #3D4956");
                }
            }
            else {
                btn_show_news.setStyle("-fx-text-fill: linear-gradient(#fb6bab, #ee2e7e, 0.3106796116504854, 1.0)");
            }
        }
        else if(event.getSource() == btn_themeswitch){
            if(darktheme == false){
                btn_themeswitch.setStyle("-fx-text-fill: #3D4956");
            }
            else{
                btn_themeswitch.setStyle("-fx-text-fill: #bcbcbc");
            }
        }
        else if(event.getSource() == btn_prefiles){
            if(savePrefs == false){
                btn_prefiles.setStyle("-fx-text-fill: #bcbcbc");
                if(darktheme == false){
                    btn_prefiles.setStyle("-fx-text-fill: #3D4956");
                }
            }
            else {
                btn_prefiles.setStyle("-fx-text-fill: linear-gradient(#fb6bab, #ee2e7e, 0.3106796116504854, 1.0)");
            }
        }
    }
    
    @FXML
    private String selDir(String file) throws FileNotFoundException, IOException{
        File myObj = new File(file);
        BufferedReader br = new BufferedReader(new FileReader(myObj));
        String data = null;
        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.contains("Selected Directory:")){
                data = line;
            }
        }
        br.close();
        return data;
    }
    
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
            
            loadLogs();
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
    
    static String lastScanned;
    
    @FXML
    private void handleButtonAction(MouseEvent event) throws IOException, AWTException, InterruptedException {
        if(event.getTarget() == btn_close){
            if(savePrefs == true){
                Antivirus_Frontend_GUI.savePreferences();
            }
            System.exit(0);
        }
        else if(event.getTarget() == btn_minimise){
            Stage stage = (Stage)anchorpane.getScene().getWindow();
            stage.setIconified(true);
        }
        else if(event.getSource() == btn_start_scan){
            Antivirus_Frontend_GUI AFG = new Antivirus_Frontend_GUI();
            AFG.spawnScanBox();
            Antivirus_Frontend_GUI.selectDir();
            DirectoryList.directoryList();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
            Date date = new Date();
            lastScan(formatter.format(date));
            lastScanned = formatter.format(date);
        }
        else if(event.getSource() == btn_opn_log){
            viewLog(returnToPath());
        }
        else if(event.getSource() == btn_del_log){
            deleteLog(returnToPath());
        }
        else if(event.getSource() == btn_autosave){
            if(asButtonDown == true){
                asButtonDown = false;
                btn_autosave.setText("OFF");
                btn_autosave.setStyle("-fx-text-fill: #bcbcbc");
                if(darktheme == false){
                    btn_autosave.setStyle("-fx-text-fill: #3D4956");
                }
            }
            else {
                asButtonDown = true;
                btn_autosave.setText("ON");
                btn_autosave.setStyle("-fx-text-fill: linear-gradient(#fb6bab, #ee2e7e, 0.3106796116504854, 1.0)");
            }
        }
        else if(event.getSource() == btn_show_news){
            if(csnButtonDown == true){
                csnButtonDown = false;
                btn_show_news.setText("OFF");
                btn_show_news.setStyle("-fx-text-fill: #bcbcbc");
                if(darktheme == false){
                    btn_show_news.setStyle("-fx-text-fill: #3D4956");
                }
                showCSN(false);
            }
            else if(csnButtonDown == false){
                csnButtonDown = true;
                btn_show_news.setText("ON");
                btn_show_news.setStyle("-fx-text-fill: linear-gradient(#fb6bab, #ee2e7e, 0.3106796116504854, 1.0)");
                showCSN(true);
            }
        }
        else if(event.getSource() == btn_themeswitch){
            if(darktheme == true){
                darktheme = false;
                btn_themeswitch.setText("LIGHT");
                btn_themeswitch.setStyle("-fx-text-fill:  #3D4956");
            }
            else{
                darktheme = true;
                btn_themeswitch.setText("DARK");
                btn_themeswitch.setStyle("-fx-text-fill:  #bcbcbc");
            }
            themeSwitch();
            loadCSN();
        }
        else if(event.getSource() == CSNlabel){
            loadCSN();
        }
        else if(event.getSource() == btn_prefiles){
            if(savePrefs == true){
                savePrefs = false;
                btn_prefiles.setText("OFF");
                btn_prefiles.setStyle("-fx-text-fill: #bcbcbc");
                if(darktheme == false){
                    btn_prefiles.setStyle("-fx-text-fill: #3D4956");
                }
            }
            else {
                savePrefs = true;
                btn_prefiles.setText("ON");
                btn_prefiles.setStyle("-fx-text-fill: linear-gradient(#fb6bab, #ee2e7e, 0.3106796116504854, 1.0)");
            }
        }
    }
    
    public static boolean darktheme;
    public static boolean asButtonDown;
    public static boolean csnButtonDown;
    public static boolean savePrefs;
    
    public void defaultPrefs(){
        darktheme = true;
        asButtonDown = true;
        csnButtonDown = true;
        savePrefs = true;
    }
    
    public void loadPrefs() throws FileNotFoundException, IOException{
        BufferedReader parser;
        parser = new BufferedReader(new FileReader(System.getenv("APPDATA") + "\\A-Team\\Config\\Preferences.txt"));
        String line;
        loadingprefs:
        while ((line = parser.readLine()) != null) {
            
            if(line.contains("loadprefs=true")){
                savePrefs = true;
                btn_prefiles.setText("ON");
                btn_prefiles.setStyle("-fx-text-fill: linear-gradient(#fb6bab, #ee2e7e, 0.3106796116504854, 1.0)");
            }
            if(line.contains("loadprefs=false")){
                defaultPrefs();
                savePrefs = false;
                btn_prefiles.setText("OFF");
                btn_prefiles.setStyle("-fx-text-fill: #bcbcbc");
                if(darktheme == false){
                    btn_prefiles.setStyle("-fx-text-fill: #3D4956");
                }
                break loadingprefs;
            }
            if(line.contains("theme=dark")){
                darktheme = true;
                btn_themeswitch.setText("DARK");
                btn_themeswitch.setStyle("-fx-text-fill:  #bcbcbc");
                themeSwitch();
                loadCSN();
            }
            if(line.contains("theme=light")){
                darktheme = false;
                btn_themeswitch.setText("LIGHT");
                btn_themeswitch.setStyle("-fx-text-fill:  #3D4956");
                themeSwitch();
                loadCSN();
            }
            if(line.contains("autosave=on")){
                asButtonDown = true;
                btn_autosave.setText("ON");
                btn_autosave.setStyle("-fx-text-fill: linear-gradient(#fb6bab, #ee2e7e, 0.3106796116504854, 1.0)");
            }
            if (line.contains("autosave=off")){
                asButtonDown = false;
                btn_autosave.setText("OFF");
                btn_autosave.setStyle("-fx-text-fill: #bcbcbc");
                if(darktheme == false){
                    btn_autosave.setStyle("-fx-text-fill: #3D4956");
                }
            }
            if (line.contains("csn=on")){
                csnButtonDown = true;
                btn_show_news.setText("ON");
                btn_show_news.setStyle("-fx-text-fill: linear-gradient(#fb6bab, #ee2e7e, 0.3106796116504854, 1.0)");
                showCSN(true);
            }
            if (line.contains("csn=off")){
                csnButtonDown = false;
                btn_show_news.setText("OFF");
                btn_show_news.setStyle("-fx-text-fill: #bcbcbc");
                if(darktheme == false){
                    btn_show_news.setStyle("-fx-text-fill: #3D4956");
                }
                showCSN(false);
            }
            if (line.contains("lastscan=")){
                lastScanned = line.substring(9);
                lastScan(lastScanned);
            }
        }
    }
    
    public void themeSwitch(){
        if(darktheme == false){
            copyright.setStyle("-fx-text-fill:  #3D4956");
            lastupdate.setStyle("-fx-text-fill:  #3D4956");
            version.setStyle("-fx-text-fill:  #3D4956");
            lastscan.setStyle("-fx-text-fill:  #3D4956");
            CSNlabel.setStyle("-fx-text-fill:  #3D4956");
            intro.setStyle("-fx-text-fill:  #3D4956");
            btn_opn_log.setStyle("-fx-text-fill:  #3D4956");
            btn_del_log.setStyle("-fx-text-fill:  #3D4956");
            as.setStyle("-fx-text-fill:  #3D4956");
            csn.setStyle("-fx-text-fill:  #3D4956");
            prefiles.setStyle("-fx-text-fill:  #3D4956");
            theem.setStyle("-fx-text-fill:  #3D4956");
            btn_scan_current.setStyle("-fx-text-fill:  #ffffff");
            btn_logs_current.setStyle("-fx-text-fill:  #ffffff");
            btn_settings_current.setStyle("-fx-text-fill:  #ffffff");
            btn_accessibility_current.setStyle("-fx-text-fill:  #ffffff");
            btn_scan.setStyle("-fx-text-fill:  #3D4956");
            btn_logs.setStyle("-fx-text-fill:  #3D4956");
            btn_settings.setStyle("-fx-text-fill:  #3D4956");
            btn_accessibility.setStyle("-fx-text-fill:  #3D4956");
            topbar.setStyle("-fx-background-color: #bcbcbc");
            csnews.setStyle("-fx-background-color: #bcbcbc");
            scan.setStyle("-fx-background-color: #bcbcbc");
            logs.setStyle("-fx-background-color: #bcbcbc");
            settings.setStyle("-fx-background-color: #bcbcbc");
            accessibility.setStyle("-fx-background-color: #bcbcbc");
        } else {
            copyright.setStyle("-fx-text-fill:  #bcbcbc");
            lastupdate.setStyle("-fx-text-fill:  #bcbcbc");
            version.setStyle("-fx-text-fill:  #bcbcbc");
            lastscan.setStyle("-fx-text-fill:  #bcbcbc");
            CSNlabel.setStyle("-fx-text-fill:  #bcbcbc");
            intro.setStyle("-fx-text-fill:  #bcbcbc");
            btn_opn_log.setStyle("-fx-text-fill:  #bcbcbc");
            btn_del_log.setStyle("-fx-text-fill:  #bcbcbc");
            as.setStyle("-fx-text-fill:  #bcbcbc");
            csn.setStyle("-fx-text-fill:  #bcbcbc");
            prefiles.setStyle("-fx-text-fill:  #bcbcbc");
            theem.setStyle("-fx-text-fill:  #bcbcbc");
            btn_scan_current.setStyle("-fx-text-fill:  #bcbcbc");
            btn_scan_current.setStyle("-fx-blend-mode: SRC_OVER");
            btn_logs_current.setStyle("-fx-text-fill:  #bcbcbc");
            btn_logs_current.setStyle("-fx-blend-mode: SRC_OVER");
            btn_settings_current.setStyle("-fx-text-fill:  #bcbcbc");
            btn_settings_current.setStyle("-fx-blend-mode: SRC_OVER");
            btn_accessibility_current.setStyle("-fx-text-fill:  #bcbcbc");
            btn_accessibility_current.setStyle("-fx-blend-mode: SRC_OVER");
            btn_scan.setStyle("-fx-text-fill:  #000000");
            btn_scan.setStyle("-fx-blend-mode: SRC_OVER");
            btn_logs.setStyle("-fx-text-fill:  #000000");
            btn_logs.setStyle("-fx-blend-mode: SRC_OVER");
            btn_settings.setStyle("-fx-text-fill:  #000000");
            btn_settings.setStyle("-fx-blend-mode: SRC_OVER");
            btn_accessibility.setStyle("-fx-text-fill:  #000000");
            btn_accessibility.setStyle("-fx-blend-mode: SRC_OVER");
            topbar.setStyle("-fx-background-color: #3D4956");
            csnews.setStyle("-fx-background-color: #3D4956");
            scan.setStyle("-fx-background-color: #3D4956");
            logs.setStyle("-fx-background-color: #3D4956");
            settings.setStyle("-fx-background-color: #3D4956");
            accessibility.setStyle("-fx-background-color: #3D4956");
        }
    }
    
    public void showCSN(boolean tf){
        csnews.setVisible(tf);
        if(tf == false){
            topbar.setLayoutX(410);
            windowcontrol.setTranslateX(-410);
            csnews.disableProperty().setValue(true);
            loadCSN();
        }
        else if(tf == true){
            topbar.setLayoutX(0);
            windowcontrol.setTranslateX(0);
            topbar.setMaxWidth(USE_COMPUTED_SIZE);
            csnews.disableProperty().setValue(false);
        }
    }
    
    private String returnToPath(){
        String inFocus = loglist.getSelectionModel().getSelectedItem();
        inFocus = inFocus.substring(0, 28);
        inFocus = inFocus.replace("Scan Log", "SCAN LOG");
        inFocus = inFocus.replace("/", ".");
        inFocus = inFocus.replace(" ", "_");
        inFocus = Antivirus_Frontend_GUI.bpath + "\\" + inFocus + ".txt";
        return inFocus;
    }
    
    private void deleteLog(String log) {
        File file = new File(log);
        file.delete();
        loadLogs();
    }
    
    private void viewLog(String log) throws IOException{
        File file = new File(log);
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }
    
    
    @FXML
    private void lastScan(String date){
        lastscan.setText("Last Scanned: " + date);
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
    
  //  public void goBack(KeyEvent event) {
  //     if(event.getCode() == KeyCode.BACK_SPACE){
  //          Platform.runLater(() -> {
  //              newspane.getEngine().executeScript("history.back()");
  //          });
  //      }
  //  }
    
    public void loadCSN(){
        if(darktheme == true){
            newspane.getEngine().loadContent("<!DOCTYPE HTML> <html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:og=\"http://opengraphprotocol.org/schema/\" xmlns:fb=\"http://www.facebook.com/2008/fbml\"> <head></head> <body style=\"margin:0 0 0 0; padding:0 0 0 0;\"> <iframe src=\"https://feed.mikle.com/widget/v2/146136/?preloader-text=Loading\" height=\"575px\" width=\"401px\" class=\"fw-iframe\" scrolling=\"no\" frameborder=\"0\"></iframe> </body> </html> ");
        }
        else {
                newspane.getEngine().loadContent("<!DOCTYPE HTML> <html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:og=\"http://opengraphprotocol.org/schema/\" xmlns:fb=\"http://www.facebook.com/2008/fbml\"> <head></head> <body style=\"margin:0 0 0 0; padding:0 0 0 0;\"> <iframe src=\"https://feed.mikle.com/widget/v2/146165/?preloader-text=Loading\" height=\"575px\" width=\"401px\" class=\"fw-iframe\" scrolling=\"no\" frameborder=\"0\"></iframe> </body> </html> ");
        }
        newspane.getChildrenUnmodifiable().addListener(new ListChangeListener<Node>() {
        @Override 
        public void onChanged(ListChangeListener.Change<? extends Node> change) {
          Set<Node> deadSeaScrolls = newspane.lookupAll(".scroll-bar");
          for (Node scroll : deadSeaScrolls) {
            scroll.setVisible(false);
          }
        }
        });
    }
    
    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadPrefs();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            defaultPrefs();
        }
        loadCSN();
        
    }    
    
}
