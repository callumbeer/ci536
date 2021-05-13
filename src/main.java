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
