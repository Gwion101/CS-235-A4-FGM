import java.awt.Color;

public class Colour {
	
	private String m_hexCode;
	private Color m_colour;
	
	public Colour(String hexCode) {
		setHex(hexCode);
		genColor();
	}
	
	public Colour(Color colour) {
		setHex(genHexString(colour));
		setColour(colour);
	}
	
	public void setHex(String hexCode) {
		if (validHex(hexCode)) {
			m_hexCode = hexCode;
		}
	}
	
	public String getHex() {
		return(m_hexCode);
	}
	
	public void setColour(Color colour) {
		m_colour = colour;
	}
	
	public Color getColour() {
		return(m_colour);
	}
	
	public void genColor() {
		m_colour = Color.decode(m_hexCode);
	}
	
	public String genHexString(Color colour) {
		String hex = "#" + Integer.toHexString((colour.getRGB() & 0x00FFFFFF));
		return(hex);
	}
	
	public boolean validHex(String hexCode) {
		try {
			Color test = Color.decode(hexCode);
		} catch (NumberFormatException e) {
			return(false);
		}
		return(true);
	}
}