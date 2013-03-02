/**
 * @file: DataVisualizerGUI.java
 * @author: Rob
 * @date: 01.03.13
 * @version: 1.8
 * 
 * This class creates all components of user interface
 * and handles user's actions
 */

import java.awt.*;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import javax.swing.JPanel;
import javax.swing.JFrame;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.PieDataset;

public class DataVisualizerGUI extends JFrame {
	public static ColourManager m_cm;
	
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 600;
	
	private GUIEventHandler m_handler;
	private final JFileChooser FILE_CHOOSER;
	
	JMenuBar m_bar;
	private JMenu m_fileMenu;
	private JMenu m_editMenu;
	private JMenu m_helpMenu;
	JMenuItem m_fileMenuItem_Open;
	JMenuItem m_fileMenuItem_Export;
	JMenuItem m_fileMenuItem_Exit;
	JMenuItem m_editMenuItem_DrawChart;
	JMenuItem m_editMenuItem_ChangeColourScheme;
	JMenuItem m_helpMenuItem_HelpContent;
	JMenuItem m_helpMenuItem_About;

	
	private JButton m_openFile_Button;
	private JButton m_exportVisualization_Button;
	private JButton m_DrawChart_Button;
	
    //private final JTabbedPane m_tabPanel  = new JTabbedPane();
    private JTabbedPane tabbedPanel;
    
	private	JPanel panel1;
	private	JPanel panel2;
	private DataSet m_dataSet;
	private DataSet m_dataSetBackup;
	
	private Chart m_chart;
	
	/**
	 * Constructs UI frame
	 */
	public DataVisualizerGUI(){

        //the handler
        m_handler = new GUIEventHandler();
        FILE_CHOOSER = new JFileChooser();
		
        setJMenuBar(createMenuBar());
        createControlPanel();
		//createTabPanel();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);	
		m_cm = new ColourManager();
		m_cm.setVisible(false);
	}
		
	/**
	 * This is the event handler for the application
	 * 
	 */
    private class GUIEventHandler implements ActionListener{

         public void actionPerformed(ActionEvent event) {
        	 
             if (event.getSource() == m_fileMenuItem_Open
            		 || event.getSource() == m_openFile_Button) {
            	 try{
            		 if (openFile()) {
            			 activateDrawAndExport();
            		 } else {
            		 	} //do not change anything when "Open File" action is cancelled
            	 } catch (NullPointerException e) {} //catches the exception in case no file is chosen
             }
             else if (event.getSource() == m_fileMenuItem_Exit) {
            	 dispose();
             } 
             else if (event.getSource() == m_exportVisualization_Button){
            	 BufferedImage bi = new BufferedImage(panel2.getSize().width, panel1.getSize().height, BufferedImage.TYPE_INT_ARGB); 
            	 Graphics g = bi.createGraphics();
            	 panel2.paint(g);  //this == JComponent
            	 g.dispose();
            	 System.out.println("printing");
            	 try{ImageIO.write(bi,"png",new File("test.png"));}catch (Exception e) {}
             }
             else if (event.getSource() ==  m_DrawChart_Button 
            		 || event.getSource() ==  m_editMenuItem_DrawChart){
 				try {
 					System.out.println("paint!");
 					m_chart.SetWindowVisible();
 					tabbedPanel.setSelectedIndex(1);
 				} catch (NullPointerException e) {
 					System.out.println("Null"); e.printStackTrace();
 					}
             } else if (event.getSource() == m_editMenuItem_ChangeColourScheme) {
            	m_cm.setVisible(true);
             } else if (event.getSource() == m_helpMenuItem_HelpContent) {
             	//Help Content pop-up window 
             } else if (event.getSource() == m_helpMenuItem_About) {
              	//About pop-up window 
             }
         }
    }

    /**
     * Creates Menu Bar
     * @return
     */  
    private JMenuBar createMenuBar() {
    	
    	m_bar = new JMenuBar();
    	createFileMenu();
    	createEditMenu();
    	createHelpMenu();	
    	return m_bar;
    }    
    
    public void redrawChartColour() {
    	JFreeChart chart;
    	if (m_chart.getChart().equals(null)) {
    		System.out.println("Error: No chart object found.");
    	} else {
    		chart = m_chart.getChart(); 
    		if (m_chart.getChartType() == -1) {
        		System.out.println("Error: No chart object found.");
        	} else if (m_chart.getChartType() == 0) {
        		XYPlot plot = chart.getXYPlot();
        		CustomBarRenderer renderer = DataVisualizerGUI.m_cm.getBarRenderer();
                plot.setRenderer(renderer);
                plot.setFixedLegendItems(DataVisualizerGUI.m_cm.getLegend(chart, renderer));
        	} else if (m_chart.getChartType() == 1) {
        		PiePlot plot = (PiePlot) chart.getPlot();
                PieDataset dataset = plot.getDataset();
                CustomPieRenderer renderer = new CustomPieRenderer(m_cm.getActiveMap());
                renderer.setColor(plot, dataset);
        	}
    	}
    }
    
    /**
     * Creates File Menu with all its options
     */
    private JMenu createFileMenu() {
        //Build the File menu.
        m_fileMenu = new JMenu("File");
        m_bar.add(m_fileMenu);
        
        //create File Menu options
        m_fileMenuItem_Open = new JMenuItem("Open File...");
        m_fileMenuItem_Open.addActionListener(m_handler); //add item to the event listener
        m_fileMenuItem_Export = new JMenuItem("Export Visualization...");
        m_fileMenuItem_Export.addActionListener(m_handler); //add item to the event listener
        m_fileMenuItem_Exit = new JMenuItem("Exit");
        m_fileMenuItem_Exit.addActionListener(m_handler); //add item to the event listener
        
        //these File Menu options are inactive before the valid data file is loaded
        m_fileMenuItem_Export.setEnabled (false);
 
        //options are added to File Menu
        m_fileMenu.add(m_fileMenuItem_Open);
        m_fileMenu.add(m_fileMenuItem_Export);
        m_fileMenu.addSeparator();
        m_fileMenu.add(m_fileMenuItem_Exit);
    	
        return m_fileMenu;
    }
    
    /**
     * Creates Edit Menu with all its options
     */
    private JMenu createEditMenu() {
        //Build the File menu.
        m_editMenu = new JMenu("Edit");
        m_bar.add(m_editMenu);
        
        //create Edit Menu options
        m_editMenuItem_DrawChart = new JMenuItem("Draw Chart");
        m_editMenuItem_DrawChart.addActionListener(m_handler); //add item to the event listener
        m_editMenuItem_ChangeColourScheme = new JMenuItem("Change Colour Scheme");
        m_editMenuItem_ChangeColourScheme.addActionListener(m_handler); //add item to the event listener
        
        //these File Menu options are inactive before the valid data file is loaded
        m_editMenuItem_DrawChart.setEnabled (false);
        m_editMenuItem_ChangeColourScheme.setEnabled (false);
 
        //options are added to File Menu
        m_editMenu.add(m_editMenuItem_DrawChart);
        m_editMenu.add(m_editMenuItem_ChangeColourScheme);
    	
        return m_editMenu;
    }
    
    /**
     * Creates Help Menu with all its options
     */
    private JMenu createHelpMenu() {
        //Build the File menu.
        m_helpMenu = new JMenu("Help");
        m_bar.add(m_helpMenu);
        
        //create Help Menu options
        m_helpMenuItem_HelpContent = new JMenuItem("Help Content");
        m_helpMenuItem_HelpContent.addActionListener(m_handler); //add item to the event listener
        m_helpMenuItem_About = new JMenuItem("About");
        m_helpMenuItem_About.addActionListener(m_handler); //add item to the event listener
        
        //options are added to File Menu
        m_helpMenu.add(m_helpMenuItem_HelpContent);
        m_helpMenu.add(m_helpMenuItem_About);
    	
        return m_helpMenu;
    }
    
    /**
     * Creates Control Panel
     * In case more button panels are needed, those panels will be added here
     * 
     */
    public void createControlPanel() {
    	
    	JPanel m_buttonPanel = createButtonPanel();
    	JTabbedPane tabPanel = createTabPanel();

    	// Line up component panels
    	JPanel controlPanel = new JPanel();
    	controlPanel.setLayout(new GridLayout(1,1));
    	controlPanel.add(m_buttonPanel);

    	// Add panel to content pane
    	add(controlPanel, BorderLayout.NORTH);
    	add(tabPanel);
    } 
    
    /**
     * Creates Options panel (quick access buttons)
     * 
     * @return
     */
    private JPanel createButtonPanel(){
        m_openFile_Button = new JButton("Open");
        m_openFile_Button.addActionListener(m_handler);
        
        m_exportVisualization_Button = new JButton("Export");
        m_exportVisualization_Button.addActionListener(m_handler);

        m_DrawChart_Button = new JButton("Draw Chart");
        m_DrawChart_Button.addActionListener(m_handler);

    	
        JPanel m_panel = new JPanel();       
        m_panel.setBorder(new TitledBorder(new EtchedBorder(), "Options"));
        m_panel.add(m_openFile_Button);
        m_panel.add(m_exportVisualization_Button);
        m_panel.add(new JSeparator(SwingConstants.VERTICAL));

        m_panel.add(m_DrawChart_Button);
        
        //these buttons are inactive before a valid data file is loaded
        m_exportVisualization_Button.setEnabled (false);        
        m_DrawChart_Button.setEnabled (false);
        
        return m_panel;
    }
    
    /**
     * Creates Tab panel (for displaying Table and Chart)
     * 
     * @return
     */
    private JTabbedPane createTabPanel(){

		// Create a tabbed panes
		tabbedPanel = new JTabbedPane();
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,1));
		tabbedPanel.addTab( "TableView", panel1);
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,1));		
		tabbedPanel.addTab( "Chart", panel2 );
		add(tabbedPanel, BorderLayout.CENTER);
		
		return tabbedPanel;
    }

  /**
   *   This method opens the file and instantiates an object of DataSet class
   */
    private Boolean openFile(){
    	m_dataSetBackup = m_dataSet; //creates a data backup in case the file is corrupted
    	FileFilter extensionFilter = new FileNameExtensionFilter("CSV Files (.csv)", "csv");
    	FILE_CHOOSER.addChoosableFileFilter(extensionFilter);
    	int m_returnVal = FILE_CHOOSER.showOpenDialog(DataVisualizerGUI.this);
    	if (m_returnVal == JFileChooser.APPROVE_OPTION) {
    		File m_file = FILE_CHOOSER.getSelectedFile();
    		System.out.println("Done");
    		m_dataSet = new DataSet(); 				//line changed
    		if(!m_dataSet.buildDataSet(m_file)){
    			final JPanel m_frame = new JPanel();
    			JOptionPane.showMessageDialog(m_frame,
    	             "The selected file is not compatible.",
    	             "File error",
    	             JOptionPane.ERROR_MESSAGE);
    			m_dataSet = m_dataSetBackup; //retrieves the data from backup when the file was corrupted
				return false;
    		} else {
    			panel1.removeAll();
    			panel1.add( new TableView(m_dataSet) );
    			panel1.repaint();
    			panel1.revalidate();
    			tabbedPanel.setSelectedIndex(0);
    			m_chart = new Chart(m_dataSet);
    			panel2.removeAll();
    			panel2.add(m_chart);
    		}
    	} else {
    		System.out.println("Cancel");
    		return false;
    	}
    	return true;
    }
   
    /**
     * This method activates Draw and Export buttons once the file is loaded
     */ 
	private void activateDrawAndExport() {
		 //these buttons and menu options are activated once the file is loaded
		 m_DrawChart_Button.setEnabled (true);
		 m_exportVisualization_Button.setEnabled (true);
		 m_editMenuItem_DrawChart.setEnabled (true);
		 m_fileMenuItem_Export.setEnabled (true); 

	}
	
	public void activateColour() {
		m_editMenuItem_ChangeColourScheme.setEnabled (true);
	}
}