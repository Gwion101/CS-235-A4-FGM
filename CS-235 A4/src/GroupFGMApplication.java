import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.data.general.Dataset;

public class GroupFGMApplication extends JFrame {
	
	private final JFileChooser FILE_CHOOSER = new JFileChooser();
	private JButton m_openFile_Button;
	Scanner m_fileScan;
	private String m_delimiter;
	
	public GroupFGMApplication(){
		Container m_container = getContentPane();
        m_container.setLayout(new FlowLayout());
        m_openFile_Button = new JButton("Open");
        m_container.add(m_openFile_Button);
        setVisible(true);
        GUIEventHandler m_handler = new GUIEventHandler();
        m_openFile_Button.addActionListener(m_handler);
	}
	
	private class GUIEventHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource()==m_openFile_Button) {
				openFile();
			} 
		}
	}
	
	private void openFile(){
		int m_returnVal = FILE_CHOOSER.showOpenDialog(GroupFGMApplication.this);
        if (m_returnVal == JFileChooser.APPROVE_OPTION) {
            File m_file = FILE_CHOOSER.getSelectedFile();
            System.out.println("Done");
            DataSet m_data = new DataSet();
            if(!m_data.buildDataSet(m_file)){
            	final JPanel m_frame = new JPanel();
				JOptionPane.showMessageDialog(m_frame,
            		    "The selected file is not compatible.",
            		    "File error",
            		    JOptionPane.ERROR_MESSAGE);
            }
        } else {
        	System.out.println("Cancel");
        }
	}
	

	public static void main(String[] args) {
		GroupFGMApplication m_mainWindow = new GroupFGMApplication();
		m_mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_mainWindow.setSize(100, 80);
	}
}