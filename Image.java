import java.util.Scanner;

public class Image {

	private int imageRows;
	private int imageCols;
	private int imageMin;
	private int imageMax;
	
	public int[][] imageArray;
	public int[][] componentArray;
	
	public Image(Scanner input) {

		if (input.hasNext()) {
			imageRows = input.nextInt();
		}
		if (input.hasNext()) {
			imageCols = input.nextInt();
		}
		if (input.hasNext()) {
			imageMax = input.nextInt();
		}
		if (input.hasNext()) {
			imageMin = input.nextInt();
		}

		initializeArrays();

	}

	public void initializeArrays() {

		imageArray = new int[imageRows + 2][imageCols + 2];
		componentArray = new int[imageRows + 2][imageCols + 2];

		for (int i = 0; i < (imageRows + 2); i++) {
			for (int j = 0; j < (imageCols + 2); j++) {
				imageArray[i][j] = 0;
				componentArray[i][j] = 0;
			}
		}

	}
	
	public void zeroFraming(int[][] array) {
		for(int i = 0; i < (imageRows + 2); i++) {
			array[i][0] = 0;
			array[i][imageCols + 1] = 0;
		}
		
		for(int i = 0; i < (imageCols + 2); i++) {
			array[0][i] = 0;
			array[imageRows + 1][i] = 0;
		}
		
	}
	
	public void loadImage(Scanner input) {
		while(input.hasNext()) {
			for (int i = 1; i < (imageRows + 1); i++) {
				for (int j = 1; j < (imageCols + 1); j++) {
					imageArray[i][j] = input.nextInt();
				}
			}
		}
	}

	public int getImageRows() {
		return imageRows;
	}

	public void setImageRows(int imageRows) {
		this.imageRows = imageRows;
	}

	public int getImageCols() {
		return imageCols;
	}

	public void setImageCols(int imageCols) {
		this.imageCols = imageCols;
	}

	public int getImageMin() {
		return imageMin;
	}

	public void setImageMin(int imageMin) {
		this.imageMin = imageMin;
	}

	public int getImageMax() {
		return imageMax;
	}

	public void setImageMax(int imageMax) {
		this.imageMax = imageMax;
	}
}
