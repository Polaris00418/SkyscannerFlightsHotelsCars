package com.github.khaliullin.skyscanner.service;

import com.github.khaliullin.skyscanner.entity.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class EmailNotifierServiceImpl implements EmailNotifierService {

    private JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void notifySubscriber(Subscription subscription, Integer oldMinPrice, Integer newMinPrice) {
        log.debug("notify Subscriber STARTED");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(subscription.getEmail());
        simpleMailMessage.setSubject("Flights Monitoring Service");
        simpleMailMessage.setText(String.format("Hello, dear! \n the price for your flight has decreased \n Old min price = %s, \n new one min price = %s, \n Subscription details = %s", oldMinPrice, newMinPrice, subscription.toString()));
        javaMailSender.send(simpleMailMessage);
        log.debug("method Notify Subscriber FINISHED");
    }

    @Override
    public void notifyAddingSubscription(Subscription subscription) {
        log.debug("method notifyAddingSubscription STARTED");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(subscription.getEmail());
        msg.setSubject("Flights Monitoring Service");
        msg.setText(String.format("Hello, dear! \n "
                + "Subscription has been successfully added. \n"
                + "Subscription details = %s", subscription.toString()));
        javaMailSender.send(msg);
        log.debug("method notifyAddingSubscription FINISHED");
    }
}
