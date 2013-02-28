/**
 * @file: TableView.java
 * @author: Rob
 * @date: 20.02.13
 * @version: 1.1
 * 
 * 
 * This class creates all components of user interface
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;


public class TableView extends JPanel {	
	
    public TableView(DataSet ds) {
        super(new GridLayout(1,0));

		final JTable table = new JTable(ds.GetData(), ds.GetAttributes()) {
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int rowIndex, int colIndex) {
	        	  return false; //Disallow the editing of any cell
	        	  }
		};
        table.setPreferredScrollableViewportSize(new Dimension(550, 600));
        table.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(table);


        add(scrollPane);
		
        this.setOpaque(true);
    }
    
    public void refresh(){
    	
    }
    
}
