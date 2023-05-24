package com.appsdeveloper.app.ws.mobileappws.shared;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.UserDto;

public class AmazonSES {
    final String FROM = "petruselgeorge@gmail.com";
    final String SUBJECT = "One last step to complete your registration with EuropeTrails";
    final String PASSWORD_RESET_SUBJECT = "Password reset request";
    final String HTML_BODY = "<h1>Please verify your email address</h1>"
            + "<p>Thank you for your registering with our mobile app. To complete registration process and be able to login click on the following link:"
            + "<a href='http://172.20.10.2:8080/mobile-app-ws/users/email-verification.html?token=$tokenValue'> "
            + "Final step to complete your registration" + "</a><br/><br/>"
            + "Thank you! ";
    final String TEXT_BODY = "Please verify your email address."
            + "Thank you for your registering with our mobile app. To complete registration process and be able to login click on the following link: "
            + "http://172.20.10.2:8080/mobile-app-ws/users/email-verification.html?token=$tokenValue"
            + "Thank you! ";

    final String PASSWORD_REQUEST_HTMl_BODY = "<h1>A request to reset your password</h1>"
            + "<p>Hi, $firstName! </p>"
            + "<p>Someone has requested to reset your password with your project. If it were not you please ignore it, "
            + "otherwise please click on the link below: "
            + "<a href='http://172.20.10.2:8080/mobile-app-ws/users/password-reset.html?token=$tokenValue'>"
            + "Click this link to reset password"
            + "</a><br/><br/>"
            + "Tank you! ";

    final String PASSWORD_REQUEST_TEXT_BODY = "A request to reset your password "
            + "Hi, $firstName! "
            + "Someone has requested to reset your password with your project. If it were not you please ignore it, "
            + "otherwise please click on the link below: "
            + "http://172.20.10.2:8080/mobile-app-ws/users/password-reset.html?token=$tokenValue"
            + "Thank you! ";

    public void verifyEmail(UserDto userDto) {
        AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).build();

        String htmlBodyWithToken = HTML_BODY.replace("$tokenValue", userDto.getEmailVerificationToken());

        String textBodyWithToken = TEXT_BODY.replace("$tokenValue", userDto.getEmailVerificationToken());

        SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(userDto.getEmail()))
                .withMessage(new Message().withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
                                .withText(new Content().withData("UTF-8").withData(textBodyWithToken)))
                        .withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
                .withSource(FROM);
        client.sendEmail(request);
        System.out.println("Email sent! ");
    }

    public boolean sendPasswordResetRequest(String firstName, String email, String token) {
        boolean returnedValue = false;
        AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).build();

        String htmlBodyWithToken = PASSWORD_REQUEST_HTMl_BODY.replace("$tokenValue", token);

        htmlBodyWithToken = htmlBodyWithToken.replace("$firstName", firstName);

        String textBodyWithToken = PASSWORD_REQUEST_TEXT_BODY.replace("$tokenValue", token);

        textBodyWithToken = textBodyWithToken.replace("$firstName", firstName);

        SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(email))
                .withMessage(new Message().withBody(new Body()
                                .withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
                                .withText(new Content().withCharset("UTF-8").withData(textBodyWithToken)))
                        .withSubject(new Content().withCharset("UTF-8").withData(PASSWORD_RESET_SUBJECT)))
                .withSource(FROM);

        SendEmailResult result = client.sendEmail(request);

        if (result != null && (result.getMessageId() != null && !result.getMessageId().isEmpty())) {
            returnedValue = true;
        }
        return returnedValue;

    }
}
