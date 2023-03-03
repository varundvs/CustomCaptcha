package org.varunk.captcha.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.varunk.captcha.service.CaptchaGenerator;

/**
 * @author varunK 18-Feb-2023
 *
 */
@RestController
@RequestMapping("/generateCaptcha")
public class GenerateCaptchaController {

    @Autowired
    CaptchaGenerator captchaGenerator;
    
    @GetMapping("/create")
    public String createCaptcha() {
        try {
            captchaGenerator.createImageCaptcha(6);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Error in creating captcha image";
        }
        return "Captcha image created sucessfully";
    }
}
