import java.util.Scanner;

public class ConnectedComponent {
	
	private int label;
	private int pixelNumber;
	
	private int minRow;
	private int minCol;
	private int maxRow;
	private int maxCol;
	
	public ConnectedComponent(Scanner input) {
		
		if(input.hasNext()) {
			this.label = input.nextInt();
		}
		if(input.hasNext()) {
			this.pixelNumber = input.nextInt();
		}
		
		if(input.hasNext()) {
			this.minRow = input.nextInt();
		}
		if(input.hasNext()) {
			this.minCol = input.nextInt();
		}
		
		if(input.hasNext()) {
			this.maxRow = input.nextInt();
		}
		if(input.hasNext()) {
			this.maxCol = input.nextInt();
		}
		
	}
	
	public void clearConnectedComponents(Image image) {
		for (int i = 0; i < image.getImageRows() + 2; i++) {
			for (int j = 0; j < image.getImageCols() + 2; j++) {
				image.componentArray[i][j] = 0;
			}
		}
	}

	public void loadConnectedComponents(int[][] imageArray, int[][] componentArray) {
		for (int i = minRow; i <= maxRow; i++) {
			for (int j = minCol; j <= maxCol; j++) {
				if(imageArray[i][j] == label) {
					componentArray[i][j] = label;
				}
			}
		}
	}
	
	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public int getPixelNumber() {
		return pixelNumber;
	}

	public void setPixelNumber(int pixelNumber) {
		this.pixelNumber = pixelNumber;
	}

	public int getMinRow() {
		return minRow;
	}

	public void setMinRow(int minRow) {
		this.minRow = minRow;
	}

	public int getMinCol() {
		return minCol;
	}

	public void setMinCol(int minCol) {
		this.minCol = minCol;
	}

	public int getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public int getMaxCol() {
		return maxCol;
	}

	public void setMaxCol(int maxCol) {
		this.maxCol = maxCol;
	}
	

}
