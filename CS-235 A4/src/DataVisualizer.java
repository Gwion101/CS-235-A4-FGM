/**
 * @file: DataVisualizer.java
 * @author: Rob
 * @date: 27.02.13
 * @version: 1.1
 * 
 * 
 * This class is the starting point of the program. It creates an instance of the UI object DataVisualizerGUI and displays it.
 */
import javax.swing.JFrame;

/**
   This program allows the user to view font effects.
*/
public class DataVisualizer {  
	
	public static DataVisualizerGUI dataVisualizer;
	
	public static void main(String[] args) {  
      dataVisualizer = new DataVisualizerGUI();
      dataVisualizer.setLocationRelativeTo(null);
      dataVisualizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      dataVisualizer.setTitle("Data Visualizer");
      dataVisualizer.setVisible(true);
	}
}