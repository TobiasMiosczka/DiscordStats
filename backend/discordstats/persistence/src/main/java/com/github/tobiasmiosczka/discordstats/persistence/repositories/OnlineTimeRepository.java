package com.github.tobiasmiosczka.discordstats.persistence.repositories;

import com.github.tobiasmiosczka.discordstats.persistence.model.OnlineTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnlineTimeRepository extends JpaRepository<OnlineTime, Long> {

}
