package com.web.api;

import com.web.service.MailService;
import com.web.utils.RandomKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
@CrossOrigin
public class MailApi {

    @Autowired
    private MailService mailService;

    @PostMapping("/send-mail")
    public ResponseEntity<String> sendMail(@RequestParam("to") String to) {

        String randomKey = RandomKey.genKey();
        String subject = "Random Key";
        String message = "random key : " + randomKey;
        mailService.sendSimpleMessage(to, subject, message);

        return ResponseEntity.ok("hay kiem tra gmail cua ban.");
    }
}
