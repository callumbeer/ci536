import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class Gui {

	private JFrame frmAntivirus;
	private JTextField txtCyberSecurityNews;
	private JTextField txtFilesScanned;
	private JTextField txtScan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmAntivirus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAntivirus = new JFrame();
		frmAntivirus.getContentPane().setBackground(new Color(220, 220, 220));
		frmAntivirus.setTitle("Antivirus");
		frmAntivirus.setBackground(new Color(255, 255, 255));
		frmAntivirus.setResizable(false);
		frmAntivirus.setBounds(100, 100, 1100, 650);
		frmAntivirus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAntivirus.getContentPane().setLayout(null);
		
		JPanel newspanel = new JPanel();
		newspanel.setBackground(new Color(192, 192, 192));
		newspanel.setBounds(0, 0, 300, 615);
		frmAntivirus.getContentPane().add(newspanel);
		newspanel.setLayout(null);
		
		JPanel titlepanel = new JPanel();
		titlepanel.setBorder(null);
		titlepanel.setBackground(new Color(192, 192, 192));
		titlepanel.setBounds(0, 0, 300, 37);
		newspanel.add(titlepanel);
		titlepanel.setLayout(null);
		
		txtCyberSecurityNews = new JTextField();
		txtCyberSecurityNews.setHorizontalAlignment(SwingConstants.CENTER);
		txtCyberSecurityNews.setForeground(new Color(0, 0, 0));
		txtCyberSecurityNews.setEditable(false);
		txtCyberSecurityNews.setFont(txtCyberSecurityNews.getFont().deriveFont(txtCyberSecurityNews.getFont().getSize() + 8f));
		txtCyberSecurityNews.setText("CYBER SECURITY NEWS");
		txtCyberSecurityNews.setBackground(new Color(192, 192, 192));
		txtCyberSecurityNews.setBounds(30, 0, 237, 39);
		titlepanel.add(txtCyberSecurityNews);
		txtCyberSecurityNews.setColumns(10);
		
		JPanel apipanel = new JPanel();
		apipanel.setBackground(new Color(224, 255, 255));
		apipanel.setBounds(0, 36, 300, 579);
		newspanel.add(apipanel);
		
		JPanel scanpanel = new JPanel();
		scanpanel.setBackground(new Color(240, 255, 255));
		scanpanel.setBounds(301, 0, 793, 615);
		frmAntivirus.getContentPane().add(scanpanel);
		scanpanel.setLayout(null);
		
		JPanel scanbox = new JPanel();
		scanbox.setBorder(new LineBorder(new Color(169, 169, 169)));
		scanbox.setBackground(new Color(245, 245, 245));
		scanbox.setBounds(257, 90, 300, 400);
		scanpanel.add(scanbox);
		scanbox.setLayout(null);
		
		JButton btnNewButton = new JButton("START SCAN");
		btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton.setBackground(new Color(220, 220, 220));
		btnNewButton.setFont(btnNewButton.getFont().deriveFont(btnNewButton.getFont().getSize() + 8f));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(comboBox.getFont().deriveFont(comboBox.getFont().getSize() - 1f));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Full Sys", "Quick", "File"}));
		comboBox.setToolTipText("");
		comboBox.setBackground(new Color(245, 245, 245));
		comboBox.setBounds(123, 0, 65, 21);
		scanbox.add(comboBox);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(0, 386, 300, 14);
		scanbox.add(progressBar);
		btnNewButton.setBounds(0, -1, 300, 70);
		scanbox.add(btnNewButton);
		
		txtFilesScanned = new JTextField();
		txtFilesScanned.setBackground(new Color(245, 245, 245));
		txtFilesScanned.setEditable(false);
		txtFilesScanned.setFont(txtFilesScanned.getFont().deriveFont(txtFilesScanned.getFont().getSize() - 1f));
		txtFilesScanned.setText("Files Scanned:");
		txtFilesScanned.setBounds(0, 68, 300, 17);
		scanbox.add(txtFilesScanned);
		txtFilesScanned.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(255, 255, 255));
		textPane.setBounds(0, 84, 300, 303);
		scanbox.add(textPane);
		
		txtScan = new JTextField();
		txtScan.setHorizontalAlignment(SwingConstants.CENTER);
		txtScan.setText("SCAN");
		txtScan.setForeground(Color.BLACK);
		txtScan.setFont(txtScan.getFont().deriveFont(txtScan.getFont().getSize() + 8f));
		txtScan.setEditable(false);
		txtScan.setColumns(10);
		txtScan.setBackground(Color.LIGHT_GRAY);
		txtScan.setBounds(0, 0, 793, 37);
		scanpanel.add(txtScan);
	}
}
