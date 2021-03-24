import java.io.FileWriter;
import java.io.IOException;

public class ChainCode {

	public Point[] neighborCoordinates;
	private Point startingPoint;
	public Point currentPoint;
	public Point nextPoint;
	public int last;
	public int next;
	public int[] zeroTable;
	public int nextDirection;
	public int chainDirection;

	public Image image;
	public ConnectedComponent component;

	public ChainCode(Image image, ConnectedComponent component) {
		this.image = image;
		this.component = component;
		neighborCoordinates = new Point[8];
		for (int i = 0; i < 8; i++) {
			neighborCoordinates[i] = new Point(-1, -1);
		}
		zeroTable = new int[] { 6, 0, 0, 2, 2, 4, 4, 6 };
	}

	public void getChainCode(ConnectedComponent component, int[][] componentArray, FileWriter output1,
			FileWriter output2) {

		int label = component.getLabel();
		int minRow = component.getMinRow();
		int minCol = component.getMinCol();
		int maxRow = component.getMaxRow();
		int maxCol = component.getMaxCol();

		boolean flag = false;
		int counter = 0;

		try {

			for (int i = minRow; i <= maxRow; i++) {
				for (int j = minCol; j <= maxCol; j++) {
					if (componentArray[i][j] == label) {

						output1.write(i + " " + j + " " + componentArray[i][j]);
						output2.write(i + " " + j + " " + componentArray[i][j]);
						System.out.print(i + " " + j + " " + componentArray[i][j]);
						startingPoint = new Point(i, j);
						currentPoint = new Point(i, j);
						last = 4;

						do {

							counter++;
							last = (last + 1) % 8;
							chainDirection = findNextPoint(currentPoint, last, nextPoint, componentArray, label);
							componentArray[currentPoint.getRow()][currentPoint.getCol()] *= (-1);
							output1.write(chainDirection + " ");
							output2.write(chainDirection + " ");
							System.out.print("\nDirection: " + chainDirection + " ");

							if (counter % 20 == 0) {
								output1.write("\n");
							}

							last = zeroTable[(chainDirection + 7) % 8];
							currentPoint = nextPoint;
						} while (!currentPoint.equals(startingPoint));
						output1.write("\n");
						output2.write("\n");
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int findNextPoint(Point currentPoint, int next, Point nextPoint, int[][] componentArray, int label) {

		loadNeighborCoordinates(currentPoint);
		int chainDirection = 0;

		for (int i = next; i < next + 8; i++) {
			if (componentArray[neighborCoordinates[i % 8].getRow()][neighborCoordinates[i % 8].getCol()] == label) {
				chainDirection = i % 8;
				System.out.println("\nPoint Found: " + chainDirection);
				break;
			}
		}

		nextPoint = new Point(neighborCoordinates[chainDirection].getRow(),
				neighborCoordinates[chainDirection].getCol());
		System.out.println("Point: " + nextPoint.getRow() + " " + nextPoint.getCol() + "\n");
		return chainDirection;

	}

	public void loadNeighborCoordinates(Point point) {

		neighborCoordinates[0] = new Point(point.getRow(), point.getCol() + 1);
		neighborCoordinates[1] = new Point(point.getRow() - 1, point.getCol() + 1);
		neighborCoordinates[2] = new Point(point.getRow() - 1, point.getCol());
		neighborCoordinates[3] = new Point(point.getRow() - 1, point.getCol() - 1);
		neighborCoordinates[4] = new Point(point.getRow(), point.getCol() - 1);
		neighborCoordinates[5] = new Point(point.getRow() + 1, point.getCol() - 1);
		neighborCoordinates[6] = new Point(point.getRow() + 1, point.getCol());
		neighborCoordinates[7] = new Point(point.getRow() + 1, point.getCol() + 1);

	}
}
