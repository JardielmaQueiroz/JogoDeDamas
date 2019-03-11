/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodedamas.screenshot;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author felip
 */
public class ScreenShot {

        
    public static final void makeScreenshot(JFrame frame, String fileName) {

        try {
            BufferedImage bufImage = new BufferedImage(frame.getSize().width, frame.getSize().height, BufferedImage.TYPE_INT_RGB);
            frame.paint(bufImage.createGraphics());            
            File imageFile = new File(fileName);
            imageFile.createNewFile();
            ImageIO.write(bufImage, "jpeg", imageFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } // makeScreenshot method

}
