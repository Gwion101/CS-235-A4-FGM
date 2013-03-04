import java.awt.Color;

public class ColourTester {

	public static void main(String[] args) {
		//Set up objects to test with.
		Colour hexColour = new Colour("#FFFFFF");
		Colour colorColour = new Colour(Color.white);
		
		//Test that constructor works the same whether using hex code or color object.
		System.out.println(hexColour.GetHex());
		System.out.println(colorColour.GetHex());
		System.out.println("Expected: #FFFFFF");
		System.out.println();
		System.out.println(hexColour.GetColour().toString());
		System.out.println(colorColour.GetColour().toString());
		System.out.println("Expected: java.awt.Color[r=255,g=255,b=255]");
		System.out.println();
		
		//Test the setHex method.
		hexColour.SetHex("#000000");
		colorColour.SetHex("#000000");
		
		System.out.println(hexColour.GetHex());
		System.out.println(colorColour.GetHex());
		System.out.println("Expected: #000000");
		System.out.println();
		
		//Test that colour corresponds to new hex.
		System.out.println(hexColour.GetColour().toString());
		System.out.println(colorColour.GetColour().toString());
		System.out.println("Expected: java.awt.Color[r=0,g=0,b=0]"); //Todo: Make sethex gen new color.
		System.out.println();
		
		//Test setColour method.
		hexColour.SetColour(Color.blue);
		colorColour.SetColour(Color.blue);
		
		System.out.println(hexColour.GetColour().toString());
		System.out.println(colorColour.GetColour().toString());
		System.out.println("Expected: java.awt.Color[r=0,g=0,b=255]");
		System.out.println();
		
		//Test that hex code corresponds to new colour.
		System.out.println(hexColour.GetHex());
		System.out.println(colorColour.GetHex());
		System.out.println("Expected: #0000FF"); //Todo: Make setcolour gen new hex.
		System.out.println();
		
		//Test genColor method.
		System.out.println(hexColour.GenColor("#FF0000"));
		System.out.println("Expected: java.awt.Color[r=255,g=0,b=0]");
		System.out.println();
		
		//Test genHexString method.
		System.out.println(hexColour.GenHexString(Color.red));
		System.out.println("Expected: #FF0000");
		System.out.println();
		
		//Test validHex method on various possible cases.
		//Lower border test, hex with numbers.
		System.out.println(hexColour.ValidHex("#000000"));
		System.out.println("Expected: True");
		System.out.println();
		
		//Normal test, hex with numbers.
		System.out.println(hexColour.ValidHex("#555555"));
		System.out.println("Expected: True");
		System.out.println();
		
		//Upper border test, hex with numbers.
		System.out.println(hexColour.ValidHex("#999999"));
		System.out.println("Expected: True");
		System.out.println();
		
		//Lower border test, hex with letters.
		System.out.println(hexColour.ValidHex("#AAAAAA"));
		System.out.println("Expected: True");
		System.out.println();
		
		//Normal test, hex with letters.
		System.out.println(hexColour.ValidHex("#CCCCCC"));
		System.out.println("Expected: True");
		System.out.println();
		
		//Upper border test, hex with letters.
		System.out.println(hexColour.ValidHex("#FFFFFF"));
		System.out.println("Expected: True");
		System.out.println();
		
		//Invalid test, hex without #.
		System.out.println(hexColour.ValidHex("FFFFFF"));
		System.out.println("Expected: False");
		System.out.println();
		
		//Invalid test, hex replacing # with other char.
		System.out.println(hexColour.ValidHex("D555555"));
		System.out.println("Expected: False");
		System.out.println();
		
		//Invalid test, hex replacing # with other number.
		System.out.println(hexColour.ValidHex("0999999"));
		System.out.println("Expected: False");
		System.out.println();
		
		//Invalid test, completely different format string.
		System.out.println(hexColour.ValidHex("Matt Adshead"));
		System.out.println("Expected: False");
		System.out.println();
	}
}
