import java.awt.Color;

/**
 * \file 	 Colour.java
 * \author 	 Matthew Adshead
 * \date	 15 Feb 2013
 * \version  1.2.2
 * 
 * \brief Colour wrapper object.
 * 
 * This class represents a singular colour within the system.
 * It consists of a Hexadecimal string representing the colour
 * and a Java Color object, and can be constructed from either
 * one of those two components.
 */

public class Colour {
	
	/**
	 * The hex code string representing the colour.
	 */
	private String m_hexCode;
	
	/**
	 * The Color object, which will be used in the recolouring of charts.
	 */
	private Color m_colour;
	
	
	/**
	 * Initialise a colour object using a hex code string.
	 * \param String representing the hex code of the colour.
	 */
	public Colour(String hexCode) {
		setHex(hexCode);
		setColour(genColor(hexCode));
	}
	
	/**
	 * Initialise a colour object using a Java Color object.
	 * \param Java Color object.
	 */
	public Colour(Color colour) {
		setHex(genHexString(colour));
		setColour(colour);
	}
	
	
	/**
	 * Mutator method for Hex String.
	 * \return Boolean representing operation success.
	 * \param String representing new Hex code.
	 */
	public boolean setHex(String hexCode) {
		if (validHex(hexCode)) {
			m_hexCode = hexCode;
			return(true);
		} else {
			return(false);
		}
	}
	
	/**
	 * Access method for Hex String.
	 * \return String representing the colour's Hex code.
	 */
	public String getHex() {
		return(m_hexCode);
	}
	
	/**
	 * Mutator method for Color object.
	 * \return Boolean representing operation success.
	 * \param String representing new Hex code.
	 */
	public boolean setColour(Color colour) {
		m_colour = colour;
		return(true);
	}
	
	/**
	 * Access method for Color object.
	 * \return Color object representing the colour.
	 */	
	public Color getColour() {
		return(m_colour);
	}
	
	/**
	 * Method for generating Color object from a given Hex code.
	 * \return Color object corresponding to the Hex code.
	 * \param String representing Hex code to convert.
	 */	
	public Color genColor(String hex) {
		return(Color.decode(hex));
	}
	
	/**
	 * Method for generating Hex code from a given Color object.
	 * \return String representing Hex code.
	 * \param Color object for converting to the Hex code.
	 */	
	public String genHexString(Color colour) {
		/*
		* This assignment creates a String containing "#" and then the RGB
		* representation of the Color object, with a special mask applied
		* to resolve it to a hex colour code. (e.g. "#AAAAAA")
		*/
		String hex = "#" + Integer.toHexString((colour.getRGB() & 0x00FFFFFF));
		
		return(hex);
	}
	
	/**
	 * Method for checking a string to see if it is a valid Hex colour code.
	 * \return Boolean - True if Hex valid, False if Hex invalid.
	 * \param String representing Hex code.
	 */
	public boolean validHex(String hexCode) {
		try {
			Color test = Color.decode(hexCode);
		} catch (NumberFormatException e) {
			return(false);
		}
		return(true);
	}
}