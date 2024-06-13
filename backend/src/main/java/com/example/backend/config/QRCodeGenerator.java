package com.example.backend.config;

import com.example.backend.user.User;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class QRCodeGenerator {

    public static final String APP_NAME = "SecurityModule";
    public static final String QR_PREFIX = "https://quickchart.io/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";

    public String generateQRUrl(User user) throws UnsupportedEncodingException {
        return QR_PREFIX + URLEncoder.encode(String.format(
                "otpauth://totp/%s:%s?secret=%s&issuer=%s",
                APP_NAME, user.getEmail(), user.getSecret(), APP_NAME), "UTF-8");
    }

}
