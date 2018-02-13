package com.github.tobiasmiosczka.discordstats.persistence.services;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuildMember;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import com.github.tobiasmiosczka.discordstats.persistence.repositories.DiscordGuildMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DiscordGuildMemberService {

    private final DiscordGuildMemberRepository discordGuildMemberRepository;

    @Autowired
    public DiscordGuildMemberService(DiscordGuildMemberRepository discordGuildMemberRepository) {
        this.discordGuildMemberRepository  = discordGuildMemberRepository;
    }

    public void addUserToGuild(DiscordUser discordUser, DiscordGuild discordGuild, Date entryDate) {
        DiscordGuildMember discordGuildMember = new DiscordGuildMember(discordUser, discordGuild, entryDate);
        try {
            discordGuildMemberRepository.save(discordGuildMember);
        } catch (DataIntegrityViolationException ex) {

        }
    }

    public void addUsersToGuild(Map<DiscordUser, Date> discordUsers, DiscordGuild discordGuild) {
        List<DiscordGuildMember> discordGuildMembers = discordUsers.entrySet().stream().map(e -> new DiscordGuildMember(e.getKey(), discordGuild, e.getValue())).collect(Collectors.toList());
        discordGuildMemberRepository.save(discordGuildMembers);
    }

    public void removeUserFromGuild(DiscordUser discordUser, DiscordGuild discordGuild) {
        DiscordGuildMember discordGuildMember = discordGuildMemberRepository.findByDiscordUserAndDiscordGuild(discordUser, discordGuild);
        discordGuildMemberRepository.delete(discordGuildMember);
    }
}
