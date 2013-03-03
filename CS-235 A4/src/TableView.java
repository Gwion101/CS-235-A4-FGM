/**
 * @file: TableView.java
 * @author: Robert Rokosz
 * @date: 02.03.13
 * @version: 1.2.1
 * 
 * 
 * This class creates a table which is displayed in a tab in main GUI
 */

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.GridLayout;


public class TableView extends JPanel {
		
    public TableView(DataSet ds) {
        super(new GridLayout(1,0));

        final JTable table = new JTable(ds.GetData(), ds.GetAttributes()) {
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int rowIndex, int colIndex) {
	        	  return false; //Disable cell editing
	        	  }
		};

        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //align all values in the table to the center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int i=0 ; i < ds.GetNoOfAttributes(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
           }    
        
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane
        		.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       
        this.add(scrollPane);		
        this.setOpaque(true);
    }  
}
