package com.github.tobiasmiosczka.discordstats.repositories;

import com.github.tobiasmiosczka.discordstats.model.platform.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{
    PasswordResetToken findByToken(String token);
}
