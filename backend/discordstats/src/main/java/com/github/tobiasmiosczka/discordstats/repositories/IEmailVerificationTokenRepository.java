package com.github.tobiasmiosczka.discordstats.repositories;

import com.github.tobiasmiosczka.discordstats.model.platform.EmailVerificationToken;
import com.github.tobiasmiosczka.discordstats.model.platform.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IEmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long>{
    EmailVerificationToken findByToken(String token);
    EmailVerificationToken findByUser(User user);
}
