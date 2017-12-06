package com.github.tobiasmiosczka.discordstats.repositories;

import com.github.tobiasmiosczka.discordstats.model.platform.VerificationToken;
import com.github.tobiasmiosczka.discordstats.model.platform.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IVerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
}
