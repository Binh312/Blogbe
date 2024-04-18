package com.web.api;

import com.web.dto.request.BlogRequest;
import com.web.entity.Blog;
import com.web.service.MailService;
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

    @PostMapping("/public/send-mail")
    public ResponseEntity<?> saveOrUpdate(){
        mailService.sendSimpleMessage("tuantatuan222@gmail.com","Test mail","don't have text");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
