# CustomCaptcha
This module help in creating our own captcha image using core java.

The module is developed in Java 1.8, and Springboot 2.7.5.

The font is embedded in the project resource / font path.

Configure the property before running the project.

Once you run the project call below URL, it will store the captcha image in the mentioned(in property file) location.

URL to generate captcha
GET: http://localhost:8051/api/generateCaptcha/create

Custom changes
1. You can try different font type.
2. You can store the file in any cloud system.
3. In response you can sent back the image as output stream.

Below are the sample captcha image generated with the code.
