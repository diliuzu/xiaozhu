package com.xzlx.commons.util;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.Email;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailUtils {

    @Autowired
    Email email;


    public void send(String subject, String msg, String... to) throws EmailException {
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();

    }
}
