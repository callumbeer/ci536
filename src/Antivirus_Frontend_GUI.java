/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
   BIG BETA UPDATE!
   Current Version: 0.4.0
  
   New Features:
   
   Scan logs(Save, view, delete)
   Cyber Sec News 
   Settings (Autosave Scan logs toggle on/off, CSNews pane toggle on/off, Save Preferences toggle on/off)
   Accessibility (Light mode)
   
   New Improvements:
  
   GUI aestetic improvements
   Last Scanned ticker saves between sessions (with preferences)

   Bug Fixes:
   Delete log feature stabalised (problems caused by resource leak in BufferedReader)
   Performance now significantly improved due to closed resource leak
   
   Misc Changes:
  
   Name Change (Team A -> A-Team)
   Removed old scan Box ( R.I.P. the dream is dead :( )
  
   WARNING: A-Team is still in early beta and you may encounter some known bugs
*/



import java.awt.BorderLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author jacks
 */
public class Antivirus_Frontend_GUI extends Application {
    
    FXMLDocumentController controller;
    public static final String logHome = System.getenv("APPDATA") + "/A-Team/Logs";
    public static final String prefHome = System.getenv("APPDATA") + "/A-Team/Config";
    public static Path prefPath = Paths.get(prefHome);
    public static Path path = Paths.get(logHome);
    public static File bpath = new File(logHome);
    public static File preferences = new File(prefHome);
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("A-Team 0.4.0");
        
        
        Image image = new Image("images/icon.png");
        stage.getIcons().add(image);
        
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        
        if (!Files.exists(prefPath)) {
            Files.createDirectories(prefPath);
        }
        
        stage.show();
        
    }
    
    
    public boolean savePopup() {
        int result = JOptionPane.showConfirmDialog(null,"Save scan log?", "Scan Log",
           JOptionPane.YES_NO_OPTION,
           JOptionPane.QUESTION_MESSAGE);
        switch (result) {
            case JOptionPane.YES_OPTION:
                return true;
            case JOptionPane.NO_OPTION:
                return false;
            default:
                return false;
        }
     }
    
    public static String selectedDirectory;
    
    public static void selectDir(){
        selectedDirectory = "";
        while(selectedDirectory.equals("")){
            DirectoryChooser directorySelector = new DirectoryChooser();
            directorySelector.setTitle("Select Directory");
            selectedDirectory = directorySelector.showDialog(null).getAbsolutePath();
            if(selectedDirectory == null){
                selectedDirectory = "";
            }
        }
        appendOutput("");
        appendOutput("Selected Directory: " + selectedDirectory);
    }
    
    public static JTextArea output;
    private final JPanel contentPanel = new JPanel(); 
    
    public void spawnScanBox() {
    JDialog JD = new JDialog();    
        JD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JD.setBounds(100, 100, 1000, 400);
                JD.setTitle("Scan");
                JD.setAlwaysOnTop(true);
                ImageIcon img = new ImageIcon("src/images/icon.png");
                JD.setIconImage(img.getImage());
                JD.toFront();
		JD.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		JD.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
                        scrollPane.setWheelScrollingEnabled(true);
                        scrollPane.setAutoscrolls(true);
			contentPanel.add(scrollPane);
			{
				output = new JTextArea();
                                DefaultCaret caret = (DefaultCaret)output.getCaret();
                                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
				output.setEditable(false);
				scrollPane.setViewportView(output);
			}
		}
        JD.setVisible(true);
        onlyOutput("Please select a directory to scan");
        //controller.deadOutput("Please select a directory to scan");
    }
    
    public static void appendOutput(String input){
        output.append(input + "\n");
        buildScanLog(input);
    }
    
    public static void onlyOutput(String input){
        output.append(input + "\n");
    }
    
    public static ArrayList<String> viruses = new ArrayList<String>();
    
    public static void buildScanLog(String file){
        viruses.add(file);
    }
    
    public static void createScanLog() throws IOException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss");
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        
        String logName = System.getenv("APPDATA") + "\\A-Team\\Logs\\SCAN_LOG_" + formatter.format(date) + ".txt";
        File scanLog = new File(logName);
        try (FileWriter logger = new FileWriter(scanLog)) {
            logger.write("Scan performed at: " + formatter1.format(date) + "\n");
            viruses.forEach(virus ->{
                try {
                    logger.write(virus + "\n");
                } catch (IOException ex) {
                    Logger.getLogger(Antivirus_Frontend_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        viruses.clear();
    }
    
    public static void savePreferences() throws IOException {
        String prefFileName = System.getenv("APPDATA") + "\\A-Team\\Config\\Preferences.txt";
        File prefs = new File(prefFileName);
        prefs.delete();
        try (FileWriter savePrefs = new FileWriter(prefs)){
            
            if(FXMLDocumentController.savePrefs = true){
                savePrefs.write("loadprefs=true\n");
            }
            else{
                savePrefs.write("loadprefs=false\n");
            }
            if(FXMLDocumentController.darktheme == true){
                savePrefs.write("theme=dark\n");
            }
            else {
                savePrefs.write("theme=light\n");
            }
            if(FXMLDocumentController.asButtonDown == true){
                savePrefs.write("autosave=on\n");
            }
            else {
                savePrefs.write("autosave=off\n");
            }
            if(FXMLDocumentController.csnButtonDown == true){
                savePrefs.write("csn=on\n");
            }
            else {
                savePrefs.write("csn=off\n");
            }
            savePrefs.write("lastscan=" + FXMLDocumentController.lastScanned);
        }
    }
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        launch(args);
//    }
    
}
