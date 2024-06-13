package com.example.backend.config.mailing;

import com.example.backend.common.ResourceReader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class EmailFactory {

    @Value("${mail.host}")
    public String systemMail;

    public Email createConfirmationEmail(String to, String username, String token) throws IOException {
        String content = ResourceReader.readHTMLFromResourcesAsString("templates/mail_template.html");
        String link = "http://localhost:8080/api/auth/confirm-registration?confirmationToken=" + token;

        content = content.replace("##1", username);
        content = content.replace("##2", link);

        return Email.builder()
                .to(to)
                .subject("Registration confirmation")
                .content(content)
                .from(systemMail)
                .build();
    }
}
