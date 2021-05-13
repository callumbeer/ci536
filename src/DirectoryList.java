

import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DirectoryList {
    
    public static String lastFile = "";
    public static boolean isSafe;
    public static int virusCount;
    public static int malCode;
    public static int sMalCode;

    public static void directoryList() throws IOException {
        String directoryName;  // directory name entered by input.
        File directory;        // file object referring to the directory.
        String[] files;        // array of file names in the directory.
        //Scanner scanner;       // for reading a line of input from the user.
        //scanner = new Scanner(System.in);  // scanner reads from standard input.
        String fileName, line = null; //ignore blank
        BufferedReader br;
        //System.out.print("Please enter a directory name: "); // directory input
        long start = System.currentTimeMillis();
        virusCount = 0;
        malCode = 0;
        
        directoryName = Antivirus_Frontend_GUI.selectedDirectory;
        directory = new File(directoryName);
        File[] flist = directory.listFiles();
        if (directory.isDirectory() == false) {
            if (directory.exists() == false)
                Antivirus_Frontend_GUI.appendOutput("There is no such directory!"); // error message if directory is not found
            else
                Antivirus_Frontend_GUI.appendOutput("That file is not a directory."); // error message for not directory input
        }
        else {
            files = directory.list();
            Antivirus_Frontend_GUI.appendOutput("Files in directory:");
            Antivirus_Frontend_GUI.appendOutput("");
            for (int i = 0; i < files.length; i++) {
                Antivirus_Frontend_GUI.appendOutput(" - " + files[i]);
            } // print the files names inside directory
        }
        Antivirus_Frontend_GUI.appendOutput("");
        Antivirus_Frontend_GUI.appendOutput("Scanning files..." + "\n");
        for (File eachFile : flist) {
            if (eachFile.isFile()) { // if statement to check file
                try {
                    //System.out.println(eachFile.getName());
                    fileName = eachFile.getAbsolutePath();
                    br = new BufferedReader(new FileReader(fileName));

                    try {
                        isSafe = true;
                        sMalCode = 0;
                        while ((line = br.readLine()) != null) { // read lines til blank
                            //System.out.println(line);
                            if(line.contains("virus")) { // if line within file contains keyword...
                                if(lastFile.equals(fileName)){
                                    //Do nothing
                                }
                                else {
                                    Antivirus_Frontend_GUI.appendOutput("Scanning file: " + fileName);
                                }
                                isSafe = false;
                                malCode ++;
                                sMalCode ++;
                            }
                            else {
                                if(lastFile.equals(fileName)){
                                    //Do nothing
                                }
                                else {
                                    Antivirus_Frontend_GUI.appendOutput("Scanning file: " + fileName);
                                }
                            }
                            lastFile = fileName;
                        }
                        if (isSafe == true){
                            Antivirus_Frontend_GUI.appendOutput("File: " + fileName + " is safe" + "\n");
                        }
                        else {
                            Antivirus_Frontend_GUI.appendOutput("WARNING! THIS FILE IS A VIRUS! : " + fileName);
                            Antivirus_Frontend_GUI.appendOutput("(" + sMalCode + " lines of malicious code detected)" + "\n");
                            virusCount ++;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(DirectoryList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    br.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DirectoryList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    } 
    long finish = System.currentTimeMillis();
    long timeElapsed = TimeUnit.MILLISECONDS.toSeconds(finish - start);
    Antivirus_Frontend_GUI.appendOutput("Scan completed in " + timeElapsed + " seconds");
    Antivirus_Frontend_GUI.appendOutput(virusCount + " infected files containing " + malCode + " lines of malicious code detected ");
    Antivirus_Frontend_GUI.onlyOutput("See above for more details");
    Antivirus_Frontend_GUI AFG = new Antivirus_Frontend_GUI();
    
    if (FXMLDocumentController.asButtonDown == true){
        Antivirus_Frontend_GUI.createScanLog();
        Antivirus_Frontend_GUI.onlyOutput("\nScan log saved successfully");
    }
    else {
        if (AFG.savePopup() == true){
            Antivirus_Frontend_GUI.createScanLog();
            Antivirus_Frontend_GUI.onlyOutput("\nScan log saved successfully");
        }
    }
    
}} // end class DirectoryList