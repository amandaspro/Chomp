import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Chomp extends Application {

	static final String PATH = "http://cs.gettysburg.edu/~tneller/cs112/chomp/images/";
	static final Image COOKIE_IMAGE = new Image(PATH + "cookie.png");
	static final Image SKULL_IMAGE = new Image(PATH + "cookie-skull.png"); 
	static final Image BLACK_IMAGE = new Image(PATH + "black.png");
	int[] orignalClick = new int[2];
	private boolean blankBoard = true;

	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
				
		Pane pane = new Pane();
		Button[] button = new Button[81];
		
		if (blankBoard == true)
		{
			System.out.print("GG");
			drawBlankState(pane, button);
			blankBoard = false;
		}
			
		if (blankBoard == false)
		{
			for (int i = 0; i < 81; i++)
			{
				int index = i;
				int[] corr = checkClicked(index);
				button[index].setOnAction(e -> {
					if (corr[0] == 0 && corr[1] == 0)
					{
						System.out.println("JJ");
						drawBlankState(pane, button);
						
					}
					else if(((ImageView) button[0].getGraphic()).getImage() == BLACK_IMAGE)
					{
						gameSetup(pane, corr, button);
					}
					else if (((ImageView) button[0].getGraphic()).getImage() == SKULL_IMAGE)
					{
						gamePlay(corr, index, pane, button);
					}
					

				});
			}
		}
		
		
		

		Scene scene = new Scene(pane, 457, 457); // Create a scene with the pane
		primaryStage.setTitle("Chomp"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		
	
		
	}
	
	
	
	public void gamePlay(int[] corr, int index, Pane pane, Button[] button)
	{
		pane.getChildren().clear();
		
		if (corr[0] == 0 && corr[1] == 0)
		{
			blankBoard  = true;
		}	
		for (int i = 0; i < 81; i++)
		{
			int x = i % 9;
			int y = i / 9;
			if (((ImageView) button[i].getGraphic()).getImage() == BLACK_IMAGE)
			{
				button[i].setGraphic(new ImageView(BLACK_IMAGE));
				button[i].setPadding(new Insets(-1, -1, -1, -1));
			}
			else if (x >= corr[0] && y >= corr[1])
			{
				button[i].setGraphic(new ImageView(BLACK_IMAGE));
				button[i].setPadding(new Insets(-1, -1, -1, -1));
			}
			else
			{
				button[i].setGraphic(new ImageView(COOKIE_IMAGE));
				button[i].setPadding(new Insets(-1, -1, -1, -1));
			}
		}
				
		
		button[0].setGraphic(new ImageView(SKULL_IMAGE));
		button[0].setPadding(new Insets(-1, -1, -1, -1));
		
		int ulx = 0;
	    int uly = 0;
	    int k = 0;
		
	    for (int r = 0; r < 9; r++)
		{
			for (int c = 0; c < 9; c++)
			{
				button[k].setLayoutX(ulx);
				button[k].setLayoutY(uly);
				pane.getChildren().add(button[k]);
				ulx += 51;
				k += 1;
			}
			ulx =0;
			uly += 51;
		}
	    
	}
	
	
	
	private void drawBlankState(Pane pane, Button[] button)
	{
		boolean isFirstTime = button[0] == null;
		int ulx = 0;
	    int uly = 0;
	    int k = 0;
		for (int i = 0; i < 81; i++)
		{
			if (isFirstTime)
				button[i] = new Button();
			button[i].setGraphic(new ImageView(BLACK_IMAGE));
			button[i].setPadding(new Insets(-1, -1, -1, -1));
		}
	    if (isFirstTime)
	    {	
	    	for (int r = 0; r < 9; r++)
	    	{	
	    		for (int c = 0; c < 9; c++)
	    		{
	    			button[k].setLayoutX(ulx);
	    			button[k].setLayoutY(uly);
	    			pane.getChildren().add(button[k]);
	    			ulx += 51;
	    			k += 1;
	    		}
	    		ulx =0;
	    		uly += 51;
	    	}	
	    }	
	}
	
	public int[] checkClicked(int clicked)
	{
		int[] corr = new int[2];
	    int x = clicked % 9;
	    int y = clicked / 9;
	    corr[0] = x;
	    corr[1] = y;
    	return corr;
	}
	
	public void gameSetup(Pane pane, int[] corr, Button[] button)
	{
		
		pane.getChildren().clear();
		button[0].setGraphic(new ImageView(SKULL_IMAGE));
		button[0].setPadding(new Insets(-1, -1, -1, -1));
		for (int i = 1; i < 81; i++)
		{
			int x = i % 9;
			int y = i / 9;
			if (x <= corr[0] && y <= corr[1])
			{
				button[i].setGraphic(new ImageView(COOKIE_IMAGE));
				button[i].setPadding(new Insets(-1, -1, -1, -1));
			}
			else
			{
				button[i].setGraphic(new ImageView(BLACK_IMAGE));
				button[i].setPadding(new Insets(-1, -1, -1, -1));
			}
		}
		int ulx = 0;
	    int uly = 0;
	    int k = 0;
		for (int r = 0; r < 9; r++)
		{
			for (int c = 0; c < 9; c++)
			{
				button[k].setLayoutX(ulx);
				button[k].setLayoutY(uly);
				pane.getChildren().add(button[k]);
				ulx += 51;
				k += 1;
			}
			ulx =0;
			uly += 51;
		}
	}
	
	
	
	
	public static void main(String[] args) 
	{
		launch(args);
	}

}
