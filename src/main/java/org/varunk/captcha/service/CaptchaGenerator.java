package org.varunk.captcha.service;

import java.io.IOException;

/**
 * @author varunK 18-Feb-2023
 *
 */
public interface CaptchaGenerator {

    String createImageCaptcha(int captchaTextLength) throws IOException;
}
