import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;


public class TableView extends JPanel {	
	
    public TableView(DataSet ds) {
        super(new GridLayout(1,0));

		//Create and set up the window.
        //JFrame frame = new JFrame("TableView");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        //final JTable table = new JTable(ds.GetData(), ds.GetAttributes());
		final JTable table = new JTable(ds.GetData(), ds.GetAttributes()) {
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int rowIndex, int colIndex) {
	        	  return false; //Disallow the editing of any cell
	        	  }
		};
        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
        table.setFillsViewportHeight(true);
        
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
		
		//Create and set up the content pane.
        this.setOpaque(true); //content panes must be opaque
        //frame.setContentPane(this);

        //Display the window.
        //frame.pack();
        //frame.setVisible(true);

    }
}
