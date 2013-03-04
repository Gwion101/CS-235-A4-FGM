import java.awt.Color;

/**
 * @file Colour.java
 * @author Matthew Adshead
 * @date 15.02.13
 * @version 1.2.2
 * @see http://www.jfree.org/jfreechart/api/javadoc/index.html and
 *      http://docs.oracle.com/javase/6/docs/api/
 * 
 *      \brief Colour wrapper object.
 * 
 *      This class represents a singular colour within the system. It consists
 *      of a Hexadecimal string representing the colour and a Java Color object,
 *      and can be constructed from either one of those two components.
 */

public class Colour {

	/**
	 * Mutator method for Hex String.
	 * 
	 * @param String
	 *            representing new Hex code.
	 * @return Boolean representing operation success.
	 */
	public boolean SetHex(String hexCode) {
		if (ValidHex(hexCode)) {
			m_hexCode = hexCode;
			return (true);
		} else {
			return (false);
		}
	}

	/**
	 * Access method for Hex String. \return String representing the colour's
	 * Hex code.
	 */
	public String GetHex() {
		return (m_hexCode);
	}

	/**
	 * Mutator method for Color object.
	 * 
	 * @param String
	 *            representing new Hex code.
	 * @return Boolean representing operation success.
	 */
	public boolean SetColour(Color colour) {
		m_colour = colour;
		return (true);
	}

	/**
	 * Access method for Color object.
	 * 
	 * @return Color object representing the colour.
	 */
	public Color GetColour() {
		return (m_colour);
	}

	/**
	 * Initialise a colour object using a hex code string.
	 * 
	 * @param String
	 *            representing the hex code of the colour.
	 */
	public Colour(String hexCode) {
		SetHex(hexCode);
		SetColour(GenColor(hexCode));
	}

	/**
	 * Initialise a colour object using a Java Color object.
	 * 
	 * @param Java
	 *            Color object.
	 */
	public Colour(Color colour) {
		SetHex(GenHexString(colour));
		SetColour(colour);
	}

	/**
	 * Method for generating Color object from a given Hex code.
	 * 
	 * @param String
	 *            representing Hex code to convert.
	 * @return Color object corresponding to the Hex code.
	 */
	public Color GenColor(String hex) {
		return (Color.decode(hex));
	}

	/**
	 * Method for generating Hex code from a given Color object.
	 * 
	 * @param Color
	 *            object for converting to the Hex code.
	 * @return String representing Hex code.
	 */
	public String GenHexString(Color colour) {

		/*
		 * This assignment creates a String containing "#" and then the RGB
		 * representation of the Color object, with a special mask applied to
		 * resolve it to a hex colour code. (e.g. "#AAAAAA")
		 */
		String hex = "#" + Integer.toHexString((colour.getRGB() & 0x00FFFFFF));

		return (hex);
	}

	/**
	 * Method for checking a string to see if it is a valid Hex colour code.
	 * 
	 * @param String
	 *            representing Hex code.
	 * @return Boolean - True if Hex valid, False if Hex invalid.
	 */
	public boolean ValidHex(String hexCode) {
		try {
			Color test = Color.decode(hexCode);
		} catch (NumberFormatException e) {
			return (false);
		}
		return (true);
	}

	/** The hex code string representing the colour. */
	private String m_hexCode;

	/** The Color object, which will be used in the recolouring of charts. */
	private Color m_colour;

} /** end class Colour */