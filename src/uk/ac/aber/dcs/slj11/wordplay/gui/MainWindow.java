package uk.ac.aber.dcs.slj11.wordplay.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;


/**
 * Main window of the word play program. Contains main method entry point.
 * @author Samuel Jackson (slj11@aber.ac.uk)
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	/**
	 * Listener for the MainWindow JFrame
	 */
	private MainWindowListener commandListener;
	
	/**
	 * JTextArea for outputting progress to the user.
	 */
	private JTextArea output;
	
	/**
	 * Construct main window
	 * Create GUI components and assign listener to components.
	 */
	public MainWindow() {
		
		//setup window
		super("WordPlay");
		commandListener = new MainWindowListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 600);
		
		//create the menubar
		JMenuBar menubar = new JMenuBar();
		
		//create file menu
		JMenu file = new JMenu("File");
		menubar.add(file);
		
		JMenuItem buildMI = new JMenuItem("Build Graph");
		buildMI.setActionCommand("build");
		buildMI.addActionListener(commandListener);
		
		JMenuItem exportMI = new JMenuItem("Export Gephi File");
		exportMI.setActionCommand("export");
		exportMI.addActionListener(commandListener);
		
		JMenuItem saveMI = new JMenuItem("Save Log");
		saveMI.setActionCommand("save");
		saveMI.addActionListener(commandListener);
		
		JMenuItem rebuildMI = new JMenuItem("Rebuild Graph");
		rebuildMI.setActionCommand("rebuild");
		rebuildMI.addActionListener(commandListener);
		
		JMenuItem exitMI = new JMenuItem("Exit");
		exitMI.setActionCommand("exit");
		exitMI.addActionListener(commandListener);
		
		file.add(buildMI);
		file.add(rebuildMI);
		file.addSeparator();
		file.add(saveMI);
		file.add(exportMI);
		file.addSeparator();
		file.add(exitMI);
		
		//create mode menu
		JMenu mode = new JMenu("Mode");
		menubar.add(mode);

		JMenuItem generateMI = new JMenuItem("Generate...");
		generateMI.setActionCommand("generate");
		generateMI.addActionListener(commandListener);
		
		JMenuItem discoverMI = new JMenuItem("Discover...");
		discoverMI.setActionCommand("discover");
		discoverMI.addActionListener(commandListener);
		
		mode.add(generateMI);
		mode.add(discoverMI);
		
		//create help menu
		JMenu help = new JMenu("Help");
		menubar.add(help);
		
		JMenuItem aboutMI = new JMenuItem("About");
		aboutMI.setActionCommand("about");
		aboutMI.addActionListener(commandListener);
		help.add(aboutMI);
		
		setJMenuBar(menubar);
		
		//creating main window objects
		JPanel mainpanel = new JPanel();
		
		//creating button panel
		JPanel btnpanel = new JPanel();
		btnpanel.setLayout(new FlowLayout());
		
		JButton discoverBtn = new JButton("Discover");
		discoverBtn.setActionCommand("discover");
		discoverBtn.addActionListener(commandListener);
		btnpanel.add(discoverBtn);
		
		JButton generateBtn = new JButton("Generate");
		generateBtn.setActionCommand("generate");
		generateBtn.addActionListener(commandListener);
		btnpanel.add(generateBtn);
		
		JButton exportBtn = new JButton("Save Log");
		exportBtn.setActionCommand("save");
		exportBtn.addActionListener(commandListener);
		btnpanel.add(exportBtn);
		
		
		//creating the output window
		JPanel outputpanel = new JPanel();
		outputpanel.setLayout(new FlowLayout());
		
		output = new JTextArea(20, 35);
		output.setEditable(false);
		
		DefaultCaret caret = (DefaultCaret)output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		JScrollPane jsp = new JScrollPane(output);
		jsp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		outputpanel.add(jsp);
		
		//build main panel
		mainpanel.setLayout(new BorderLayout());
		mainpanel.add(btnpanel, BorderLayout.NORTH);
		mainpanel.add(outputpanel, BorderLayout.CENTER);
		
		add(mainpanel, BorderLayout.CENTER);
		pack();

		//create window in centre of screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 
		int w = getSize().width;
		int h = getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		 
		setLocation(x, y);
		
		//Update the output window with some date/time data
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		
		updateOutput("WORDPLAY\n--------------------------------------------------------------");
		updateOutput("Date: " + dateFormat.format(date));
		updateOutput("Time: " + timeFormat.format(date));
		updateOutput("Ready...");
		
		setVisible(true);
	}
	
	/**
	 * Main entry point for application.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		new MainWindow();
	}

	/**
	 * Append a string to the output text area.
	 * @param update String to append to the area
	 */
	public void updateOutput(String update) {
		output.append(update + "\n");
	}
	
	/**
	 * Get the contents of the output window.
	 * @return Output window contents
	 */
	public String getLog() {
		return output.getText();
	}
}
