package org.varunk.captcha.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.AttributedString;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 * @author varunK 18-Feb-2023
 *
 */
@Service
public class ImageCaptchaGeneratorImpl implements CaptchaGenerator {

    private static final Logger log = LoggerFactory.getLogger(ImageCaptchaGeneratorImpl.class);

    @Value("${file.storage.path}")
    String                      FILE_LOCATION;

    public String createImageCaptcha(int captchaTextLength) throws IOException {

        final String alphabet = "123456789AbcdefGhiJklmnPQrstuvwxYz";
        final int N = alphabet.length();

        Random r = new Random();

        // Generate Captcha String - Use random alphabets
        StringBuilder randomCaptcha = new StringBuilder();
        for (int i = 0; i < captchaTextLength; i++) {
            randomCaptcha.append(alphabet.charAt(r.nextInt(N)));
        }
        log.info("Captcha: " + randomCaptcha.toString());

        // Style image
        Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
        attributes.put(TextAttribute.TRACKING, 0.3);

        // Load font from class path
        File ff = ResourceUtils.getFile("classpath:fonts/AlienMarksman.ttf");
        Font f = null;
        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(ff));

        } catch (FontFormatException e) {
            log.error("Error while loading font");
            e.printStackTrace();
        }

        // Generate captcha image from string
        BufferedImage image = new BufferedImage(220, 60, BufferedImage.TYPE_INT_RGB);

        // Create 2d graphic image
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, 250, 100);  /// Fill colour
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(f);

            // Create font object from the loaded font and use it in creating image
            Font font = new Font("Alien Marksman", Font.LAYOUT_LEFT_TO_RIGHT, 40);
//            log.info(font != null ? "true" : "false");

            Font font2 = font.deriveFont(attributes);

            // Design the captcha image
            AttributedString as1 = new AttributedString(randomCaptcha.toString());
            as1.addAttribute(TextAttribute.FONT, font2);
            as1.addAttribute(TextAttribute.FOREGROUND, Color.BLACK, 0, captchaTextLength);
            as1.addAttribute(TextAttribute.BACKGROUND, Color.WHITE, 0, captchaTextLength);

            // To stick the captcha text uncomment the below line
            // as1.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON, 0, 5);
            g2d.drawString(as1.getIterator(), 10, 40);

        } catch (Exception e) {
            log.error("Error while setting font to image");
            e.printStackTrace();
        }
        try {
            // Save the image in the project folder or to specific path on the server
            String fileName = UUID.randomUUID().toString();
            File outputfile = new File(FILE_LOCATION + fileName + ".jpg");
            ImageIO.write(image, "jpg", outputfile);

            // Include code below to store the image on cloud

        } catch (Exception e) {
            log.error("Error while saving file to given path");
            e.printStackTrace();
        }

        // Send back image url or image input stream (byte) as response
        return null;
    }
}