package com.github.tobiasmiosczka.discordstats.repositories;

import com.github.tobiasmiosczka.discordstats.model.platform.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = ?1 OR u.email = ?1")
    User findByUsernameOrEmail(String usernameOrEmail);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM User u WHERE u.username = ?1")
    boolean existsWithUsername(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM User u WHERE u.email = ?1")
    boolean existsWithEmail(String email);
}
