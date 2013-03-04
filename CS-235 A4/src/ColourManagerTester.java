public class ColourManagerTester {

	public static void main(String[] args) {
		//Set up objects to test on.
		ColourManager cm = new ColourManager();
		Colour[] five = new Colour[5];
		Colour white = new Colour("#FFFFFF");
		five[0] = white;
		Colour black = new Colour("#000000");
		five[1] = black;
		Colour red = new Colour("#FF0000");
		five[2] = red;
		Colour green = new Colour("#00FF00");
		five[3] = green;
		Colour blue = new Colour("#0000FF");
		five[4] = blue;
		ColourMap fivecolours = new ColourMap(five, "Five Colours");
		
		//Test getActiveMap method.
		System.out.println(cm.GetActiveMap().GetMapName());
		System.out.println("Expected: Greyscale");
		System.out.println();
		
		//Test setActiveMap method with valid index and invalid index.
		cm.SetActiveMap(1);
		
		System.out.println(cm.GetActiveMap().GetMapName());
		System.out.println("Expected: Nice Colours");
		System.out.println();
		
		try {
			cm.SetActiveMap(2);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Caught exception.");
		}
		System.out.println("Expected: Caught exception.");
		System.out.println();
		cm.SetActiveMap(0);
		
		//Test getMap method with valid index and invalid index.
		System.out.println(cm.GetMap(0).GetMapName());
		System.out.println("Expected: Greyscale");
		System.out.println();
		
		System.out.println(cm.GetMap(1).GetMapName());
		System.out.println("Expected: Nice Colours");
		System.out.println();
		
		try {
			System.out.println(cm.GetMap(2).GetMapName());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Caught exception.");
		}
		System.out.println("Expected: Caught exception.");
		System.out.println();
		
		//Test setMap method with valid index and invalid index.
		cm.SetMap(1, fivecolours);
		System.out.println(cm.GetMap(0).GetMapName());
		System.out.println("Expected: Greyscale");
		System.out.println();
		System.out.println(cm.GetMap(1).GetMapName());
		System.out.println("Expected: Five Colours");
		System.out.println();
		
		try {
			cm.SetMap(2, fivecolours);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Caught exception.");
		}
		System.out.println("Expected: Caught exception.");
		System.out.println();
	}

}
