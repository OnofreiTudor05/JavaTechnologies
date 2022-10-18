package captchagenerator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Tudor Onofrei
 */
public class ImageGenerator {
    static BufferedImage bufferedImage;
    static Graphics2D g;
    private int angleNumber;
    
    public ImageGenerator(){
        bufferedImage = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
        
        angleNumber = (int)(Math.random() * 12 + 3);
        RegularPolygon finalDrawing = new RegularPolygon(150, 150, 125, angleNumber);
        g = bufferedImage.createGraphics();
        
        for(int pointIndex = 0; pointIndex < angleNumber; pointIndex++){
            int shapeSize = (int)(Math.random() * 10) + 25;
            RegularPolygon shape = new RegularPolygon(finalDrawing.xpoints[pointIndex], 
                    finalDrawing.ypoints[pointIndex], shapeSize, (int)(Math.random() * 10 + 3));
            g.setColor(new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
            g.fill(shape);
        }
        
        g.setColor(new Color(0, 0, 0));
        g.fill(finalDrawing);
    }
    
    static BufferedImage getBufferedImage(){
        return bufferedImage;
    }
    
    static Graphics2D getGraphics(){
        return g;
    }

    public int getAngleNumber() {
        return angleNumber;
    }

    public void setAngleNumber(int angleNumber) {
        this.angleNumber = angleNumber;
    }
    
    
    
}
