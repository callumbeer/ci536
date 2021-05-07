/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Scan feature now implemented!
 * Current Version: 0.3
 *
 * First functional prototype version with operational
 * front + backend integration, limited functionality
*/



import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author jacks
 */
public class Antivirus_Frontend_GUI extends Application {
    
    FXMLDocumentController controller;
    
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
        stage.setTitle("Team-A Antivirus (v0.3 alpha)");
        stage.show();
        
        Image image = new Image("images/icon.png");
        stage.getIcons().add(image);
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
				output.setEditable(false);
				scrollPane.setViewportView(output);
			}
		}
        JD.setVisible(true);
        appendOutput("Please select a directory to scan");
        //controller.deadOutput("Please select a directory to scan");
    }
    
    public static void appendOutput(String input){
        output.append(input + "\n");
    }
    
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        launch(args);
//    }
    
}
