/**
 * @file: TableView.java
 * @author: Rob
 * @date: 28.02.13
 * @version: 1.2
 * 
 * 
 * This class creates a table which is displayed in a tab in main GUI
 */

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.Dimension;
import java.awt.GridLayout;


public class TableView extends JPanel {
		
    public TableView(DataSet ds) {
        super(new GridLayout(1,1));

        final JTable table = new JTable(ds.GetData(), ds.GetAttributes()) {
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int rowIndex, int colIndex) {
	        	  return false; //Disable cell editing
	        	  }
		};
        table.setPreferredScrollableViewportSize(new Dimension(550, 600));
        table.setFillsViewportHeight(true);

        //align all values to the center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int i=0 ; i < ds.GetNoOfAttributes(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
           }
        
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);		
        this.setOpaque(true);
    }  
}
