import java.awt.Color;

public class ColourMapTester {

	public static void main(String[] args) {
		//Setting up some objects to test.
		Colour[] four = new Colour[4];
		Colour[] five = new Colour[5];
		Colour[] six = new Colour[6];
		
		Colour white = new Colour("#FFFFFF");
		four[0] = white;
		five[0] = white;
		six[0] = white;
		Colour black = new Colour("#000000");
		four[1] = black;
		five[1] = black;
		six[1] = black;
		Colour red = new Colour("#FF0000");
		four[2] = red;
		five[2] = red;
		six[2] = red;
		Colour green = new Colour("#00FF00");
		four[3] = green;
		five[3] = green;
		six[3] = green;
		Colour blue = new Colour("#0000FF");
		five[4] = blue;
		six[4] = blue;
		Colour bluegreen = new Colour("#00FFFF");
		six[5] = bluegreen;
		
		
		//Test that <5 objects can be added.
		ColourMap fourcolours = new ColourMap(four, "Four");
		System.out.println("Expected: No output.");
		System.out.println();
		
		//Test that =5 objects can be added.
		ColourMap fivecolours = new ColourMap(five, "Five");
		System.out.println("Expected: No output.");
		System.out.println();
		
		//Test that if >5 objects are added, error shows and first 5 are used.
		ColourMap sixcolours = new ColourMap(six, "Six");
		System.out.println("Expected: Error message.");
		System.out.println();
		
		//Test the getColour method.
		for (int i=0; i<5; i++) {
			System.out.print(fivecolours.GetColour(i).GetHex() + " ");
		}
		System.out.println();
		System.out.println("Expected: #FFFFFF #000000 #FF0000 #00FF00 #0000FF");
		System.out.println();
		
		//Test the setColour method.
		for (int i=0; i<5; i++) {
			fivecolours.SetColour(i, new Colour("#FFFFFF"));
		}
		for (int i=0; i<5; i++) {
			System.out.print(fivecolours.GetColour(i).GetHex() + " ");
		}
		System.out.println();
		System.out.println("Expected: #FFFFFF #FFFFFF #FFFFFF #FFFFFF #FFFFFF ");
		System.out.println();
		
		//Test the getMapName method.
		System.out.println(fivecolours.GetMapName());
		System.out.println("Expected: Five");
		System.out.println();
		
		//Test the setMapName method.
		fivecolours.SetMapName("ELEPHANT");
		
		System.out.println(fivecolours.GetMapName());
		System.out.println("Expected: ELEPHANT");
		System.out.println();
		
		//Test the getNumber method.
		System.out.println(fivecolours.GetNumber());
		System.out.println("Expected: 5");
		System.out.println();
		System.out.println(sixcolours.GetNumber());
		System.out.println("Expected: 5");
		System.out.println();
	}
}
