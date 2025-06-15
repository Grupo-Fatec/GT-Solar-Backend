package org.github.gabrielgodoi.gtsolarbackend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Globals {

    @Value("${spring.mail.username}")
    private String email;

}

