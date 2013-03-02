/**
 * \file 	 ColourMap.java
 * \author 	 Matthew Adshead
 * \date	 15 Feb 2013
 * \version  1.5
 * 
 * \brief Object representing a colour scheme.
 * 
 * This class represents a colour scheme within the system.
 * It consists of a number of Colour objects stored in an array,
 * and a String representing the colour scheme name.
 */

public class ColourMap {
	
	/**
	 * Constant defining the size of a colour map. (i.e. How many colours it contains.)
	 */
	private final int m_mapSize = 5;
	
	/**
	 * The array containing the colour objects.
	 */
	private Colour[] m_colourMap = new Colour[m_mapSize];
	
	/**
	 * String representing the colour map name.
	 */
	private String m_mapName;
	
	
	/**
	 * Initialises a colour map.
	 * \param colours - Array of colour objects.
	 * \param name - Name of colour map.
	 */
	public ColourMap(Colour[] colours, String name) {
		initMap(); //First initialises array to null.
		if (colours.length <= m_mapSize) {
			//Then if array parameter is <= max number of maps, 
			for (int i=0; i<colours.length; i++) {
				setColour(i, colours[i]); //transfer colours to object array.
			}
		} else {
			//If array parameter is > max number of maps, 
			for (int i=0; i<m_mapSize; i++) {
				//transfer until max number is reached and ignore excess.
				setColour(i, colours[i]);
			}
			//Print error message to console, to let user know array was too big.
			System.out.println("Error: The array you are using is too big. A" +
			" ColourMap can only hold " + m_mapSize + " colours. The first " +
			m_mapSize + " have been used.");
		}
		//Finally, set the map name.
		setMapName(name);
	}
	
	/**
	 * Initialises the object Colour array such that all fields are null.
	 */
	public void initMap() {
		for (int i=0; i<5; i++) {
			setColour(i, null);
		}
	}
	
	/**
	 * Mutator method for items in the Colour array.
	 * \return Boolean representing operation success.
	 * \param i - Array index to set.
	 * \param colour - New Colour object.
	 */
	public boolean setColour(int i, Colour colour) {
		m_colourMap[i] = colour;
		return(true);
	}
	
	/**
	 * Access method for items in Colour array.
	 * \return Colour object at index i in the array.
	 * \param i - Array index.
	 */
	public Colour getColour(int i) {
		return(m_colourMap[i]);
	}
	
	/**
	 * Finds the number of Colour objects in the array.
	 * \return Int representing the number of Colour objects in the array.
	 */
	public int getNumber() {
		int num = 0;
		for (int i=0; i<m_colourMap.length; i++) {
			if (!(m_colourMap[i].equals(null))) {
				num++;
			}
		}
		return(num);
	}
	
	/**
	 * Mutator method for map name.
	 * \return Boolean representing operation success.
	 * \param name - New name for map.
	 */
	public boolean setMapName(String name) {
		m_mapName = name;
		return(true);
	}
	
	/**
	 * Access method for the map name.
	 * \return String representing map name.
	 */
	public String getMapName() {
		return(m_mapName);
	}
}