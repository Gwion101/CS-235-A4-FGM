/**
 * @file: DataVisualizerGUI.java
 * @author: Rob
 * @date: 27.02.13
 * @version: 1.0
 * 
 * 
 * This class creates all components of user interface
 */

import java.awt.*;
//import java.awt.event.*;
import java.io.File;
import java.io.IOException;
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

public class DataVisualizerGUI extends JFrame {
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 800;
	
	private GUIEventHandler m_handler;
	private final JFileChooser FILE_CHOOSER;
	
	JMenuBar m_bar;
	private JMenu m_fileMenu;
	private JMenu m_editMenu;
	private JMenu m_helpMenu;
	JMenuItem m_fileMenuItem_Open;
	JMenuItem m_fileMenuItem_Export;
	JMenuItem m_fileMenuItem_Exit;

	
	private JButton m_openFile_Button;
	private JButton m_exportVisualization_Button;
	private JButton m_DrawChart_Button;
	
    //private final JTabbedPane m_tabPanel  = new JTabbedPane();
    private JTabbedPane tabbedPanel;
    
	private	JPanel panel1;
	private	JPanel panel2;
	private DataSet m_data;
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
            		 
            		 openFile();
            		 
            		 //TableView table = new TableView(m_data); //for the purpose of testing, Table View window displays the data
            	 } catch (NullPointerException e) {} //catches the exception in case no file is chosen
             }
             if (event.getSource() == m_fileMenuItem_Exit) {
            	 dispose();
             } 
             if (event.getSource() == m_exportVisualization_Button){
            	 BufferedImage bi = new BufferedImage(panel2.getSize().width, panel1.getSize().height, BufferedImage.TYPE_INT_ARGB); 
            	 Graphics g = bi.createGraphics();
            	 panel2.paint(g);  //this == JComponent
            	 g.dispose();
            	 System.out.println("printing");
            	 try{ImageIO.write(bi,"png",new File("test.png"));}catch (Exception e) {}
             }
             if (event.getSource() ==  m_DrawChart_Button){
 				try {
 					System.out.println("paint!");
 					m_chart.SetWindowVisible();
 					tabbedPanel.setSelectedIndex(1); 					
 					
 					/*m_ChartSetup = new ChartSetup(m_data);
 					m_ChartSetup.setSize(new Dimension(600,400));
 					
 				} catch (IOException e) {
 					// TODO Auto-generated catch block
 					//e.printStackTrace();*/
 				} catch (NullPointerException e) {System.out.println("Null"); e.printStackTrace();}
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
        
        //Build the Edit menu.
        m_editMenu = new JMenu("Edit");
        m_bar.add(m_editMenu);
        
        //Build the Help menu.
        m_helpMenu = new JMenu("Help");
        m_bar.add(m_helpMenu);
    	
        //a group of JMenuItems

    	return m_bar;
    }    
    
    /**
     * Creates File menu and all its options
     */
    private JMenu createFileMenu() {
        //Build the File menu.
        m_fileMenu = new JMenu("File");
        m_bar.add(m_fileMenu);
        //add(m_fileMenu);
        
        m_fileMenuItem_Open = new JMenuItem("Open File...");
        m_fileMenuItem_Open.addActionListener(m_handler); //add item to the event listener
        m_fileMenuItem_Export = new JMenuItem("Export Visualization...");
        m_fileMenuItem_Export.addActionListener(m_handler); //add item to the event listener
        m_fileMenuItem_Exit = new JMenuItem("Exit");
        m_fileMenuItem_Exit.addActionListener(m_handler); //add item to the event listener
        
        m_fileMenu.add(m_fileMenuItem_Open);
        m_fileMenu.add(m_fileMenuItem_Export);
        m_fileMenu.addSeparator();
        m_fileMenu.add(m_fileMenuItem_Exit);
    	
        return m_fileMenu;
    }
    
    
    /**
     * Creates Control Panel
     * In case we need more button panels, those panels will be added here
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
        //m_panel.add(Box.createHorizontalStrut(5));
        m_panel.add(new JSeparator(SwingConstants.VERTICAL));
        //m_panel.add(new javax.swing.JPopupMenu.Separator());
        //m_panel.add(Box.createHorizontalStrut(5));
        m_panel.add(m_DrawChart_Button);
        
        return m_panel;
    }
    
    
    private JTabbedPane createTabPanel(){

		// Create a tabbed pane
		tabbedPanel = new JTabbedPane();
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,1));
		tabbedPanel.addTab( "TableView", panel1);
		//createPage1();
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,1));
		
		tabbedPanel.addTab( "Chart", panel2 );
		add(tabbedPanel, BorderLayout.CENTER);
		
		return tabbedPanel;
    }
/*    
    private void initTabComponent(int i) {
        pane.setTabComponentAt(i, new ButtonTabComponent(pane));
    }
    
    
	public void createPage1()
	{
		panel1 = new JPanel();
		//panel1.setLayout( null );

		//panel1.add( new TableView(m_data) );


	}
*/
    
  
    
  /**
   *   This method opens the file and instantiates an object of DataSet class
   */
    
    private void openFile(){
    	int m_returnVal = FILE_CHOOSER.showOpenDialog(DataVisualizerGUI.this);
    	if (m_returnVal == JFileChooser.APPROVE_OPTION) {
    		File m_file = FILE_CHOOSER.getSelectedFile();
    		System.out.println("Done");
    		m_data = new DataSet(); 				//line changed
    		if(!m_data.buildDataSet(m_file)){
    			final JPanel m_frame = new JPanel();
    			JOptionPane.showMessageDialog(m_frame,
    	             "The selected file is not compatible.",
    	             "File error",
    	             JOptionPane.ERROR_MESSAGE);
    		} else { 
    			panel1.removeAll();
    			panel1.add( new TableView(m_data) );
    			panel1.repaint();
    			panel1.revalidate();
    			tabbedPanel.setSelectedIndex(0);
    			m_chart= new Chart(m_data);
    			panel2.removeAll();
    			panel2.add( m_chart);
    		}
    	} else {
    		System.out.println("Cancel");
    	}
    }
}
