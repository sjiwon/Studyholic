package com.sjiwon.studyholic.domain.mail;

import com.sjiwon.studyholic.domain.user.repository.UserRepository;
import com.sjiwon.studyholic.exception.StudyholicException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.sjiwon.studyholic.common.VariableFactory.*;
import static com.sjiwon.studyholic.exception.StudyholicErrorCode.DUPLICATE_USER_EMAIL;
import static com.sjiwon.studyholic.exception.StudyholicErrorCode.EMAIL_NEVER_AUTHENTICATED;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailService {
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAddress;

    public String sendEmailAuthenticationNonce(String emailType, String email) {
        String cryptographicNonce = createCryptographicNonce(); // nonce
        SimpleMailMessage message = getDefaultMailMessage(email);

        switch (emailType) {
            case "join" -> {  // 회원가입 이메일 (join)
                checkDuplicateEmail(email);
                message.setSubject(AUTHENTICATION_EMAIL_SUBJECT);
                message.setText(AUTHENTICATION_EMAIL_BODY + " : " + cryptographicNonce);
            }
            case "id" -> {  // 아이디 찾기 이메일 (id)
                checkExistsEmail(email);
                message.setSubject(AUTHENTICATION_LOGIN_ID_SUBJECT);
                message.setText(AUTHENTICATION_EMAIL_BODY + " : " + cryptographicNonce);
            }
            case "password" -> {  // 비밀번호 찾기 이메일 (password)
                checkExistsEmail(email);
                message.setSubject(AUTHENTICATION_PASSWORD_SUBJECT);
                message.setText(AUTHENTICATION_EMAIL_BODY + " : " + cryptographicNonce);
            }
            case "randomPassword" -> {  // 임시 비밀번호 발급 이메일
                checkExistsEmail(email);
                message.setSubject(RANDOM_PASSWORD_SUBJECT);
                message.setText(RANDOM_PASSWORD_EMAIL_BODY + " : " + cryptographicNonce);
            }
        }
        mailSender.send(message);

        return cryptographicNonce;
    }

    private void checkDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw StudyholicException.type(DUPLICATE_USER_EMAIL);
        }
    }

    private void checkExistsEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw StudyholicException.type(EMAIL_NEVER_AUTHENTICATED);
        }
    }

    private SimpleMailMessage getDefaultMailMessage(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom(fromAddress);
        return message;
    }

    private String createCryptographicNonce() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
