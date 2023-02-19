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

![68ab3537-90f1-4e6c-a06b-96f111d31748](https://user-images.githubusercontent.com/5154470/219947359-dffd963b-9683-4189-92ce-288d0a60b1ef.jpg)

![d8d7d489-7100-4948-bdfc-c7043f40fb5b](https://user-images.githubusercontent.com/5154470/219947365-4b628236-5c88-4ca7-82d0-0f5453a1eecc.jpg)

![9e082b1e-c9c0-4f0c-8f4f-54977b240f3d](https://user-images.githubusercontent.com/5154470/219947367-a6b597e3-9c30-40b8-bf92-ce05d828fb3d.jpg)

![7bb7b0bf-a889-4a20-abbf-6e3e456c4144](https://user-images.githubusercontent.com/5154470/219947368-3ff51630-0a24-4274-83af-2c7ad1b79a4e.jpg)
