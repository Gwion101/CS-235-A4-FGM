/**
 * @file: DataVisualizer.java
 * @author: Rob
 * @date: 27.02.13
 * @version: 1.0
 * 
 * 
 * This class creates all components of user interface
 */
import javax.swing.JFrame;

/**
   This program allows the user to view font effects.
*/
public class DataVisualizer
{  
   public static void main(String[] args)
   {  

      JFrame dataVisualizer = new DataVisualizerGUI();
      dataVisualizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      dataVisualizer.setTitle("Data Visualizer");
      dataVisualizer.setVisible(true);
      
   }
}

