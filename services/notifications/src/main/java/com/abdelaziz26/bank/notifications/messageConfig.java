package com.abdelaziz26.bank.notifications;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class messageConfig {

    private final JavaMailSender mailSender;
    private static final Logger logger = LoggerFactory.getLogger(messageConfig.class);

    @Value("${SMTP_USERNAME}")
    private String From;


    @Bean
    public Function<AccountMsgDto, AccountMsgDto> email() throws MessagingException
    {
        return accountMsgDto -> {
            try {
                mailSender.send(this.getMimeMsg(accountMsgDto));
                logger.info("An email successfully sent to user with Email {}", accountMsgDto.email());
                return accountMsgDto;
            }
            catch (MessagingException e) {
                logger.error(e.getMessage());
            }
            return null;
        };
    }

    @Bean
    public Function<AccountMsgDto, Long> sms()
    {
        return accountMsgDto -> {
            logger.info("An SMS successfully sent to user with Phone number {}", accountMsgDto.phone());
            return accountMsgDto.accountNumber();
        };
    }

    private SimpleMailMessage getMimeMsg(AccountMsgDto accountMsgDto) throws MessagingException
    {
        SimpleMailMessage mimeMessage = new SimpleMailMessage();
        mimeMessage.setFrom(From);
        mimeMessage.setText("Congrats! You bank account has been successfully registered!");
        mimeMessage.setTo(accountMsgDto.email());
        mimeMessage.setSubject("Welcome on board! bankerSite");
        return mimeMessage;
    }
}
