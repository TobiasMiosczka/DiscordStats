package com.github.tobiasmiosczka.discordstats.rest.repositories;

import com.github.tobiasmiosczka.discordstats.rest.model.platform.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{
    PasswordResetToken findByToken(String token);
}
