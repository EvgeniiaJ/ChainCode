import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String imageFileName;
		String propertyFileName;
		String codesFileName;
		String debugingFileName;

		if (args.length != 4) {
			System.out.println("Invalid Number of Arguments.");
			System.out.println("Enter names of image file, property file, file for codes output, and file for debuging.");
			System.exit(0);
		}

		try {
			imageFileName = args[0];
			propertyFileName = args[1];
			codesFileName = args[2];
			debugingFileName = args[3];
			
			int componentsNumber = -1;

			Scanner image = new Scanner(new File(imageFileName));
			Scanner property = new Scanner(new File(propertyFileName));
			FileWriter codes = new FileWriter(new File(codesFileName));
			FileWriter debuging = new FileWriter(new File(debugingFileName));
			
			Image picture = new Image(image);
			picture.zeroFraming(picture.imageArray);
			picture.loadImage(image);
			
			codes.write(picture.getImageRows() + " " + picture.getImageCols() + " " + picture.getImageMin() + " " + picture.getImageMax() + "\n");
			
			int numRows = -1, numCols = -1, minVal = -1, maxVal = -1, numComponents = -1;
			
			if(property.hasNext()) {
				numRows = property.nextInt();
			}
			if(property.hasNext()) {
				numCols = property.nextInt();
			}
			
			if(property.hasNext()) {
				minVal = property.nextInt();
			}
			if(property.hasNext()) {
				maxVal = property.nextInt();
			}
			
			if(property.hasNext()) {
				numComponents = property.nextInt();
			}
			
			int currentComponent = 1;
			ConnectedComponent component;
			ChainCode chainCode;
			
			debuging.write(numRows + " " + numCols + " " + minVal + " " + maxVal + "\n");
			
			while(currentComponent <= numComponents) {
				component = new ConnectedComponent(property);
				component.clearConnectedComponents(picture);
				component.loadConnectedComponents(picture.imageArray, picture.componentArray);
				chainCode = new ChainCode(picture, component);
				chainCode.getChainCode(component, picture.componentArray, codes, debuging);
				currentComponent++;
			}
			
			
			 

			image.close();
			property.close();
			codes.close();
			debuging.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
