package com.github.tobiasmiosczka.discordstats.rest;

import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordGuildService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordUserService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelUsageService;
import com.github.tobiasmiosczka.discordstats.persistence.exception.DiscordGuildNotFoundException;
import com.github.tobiasmiosczka.discordstats.rest.web.controller.GuildController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GuildController.class)
public class GuildControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscordGuildService discordGuildService;

    @MockBean
    private DiscordUserService discordUserService;

    @MockBean
    private DiscordVoiceChannelUsageService discordVoiceChannelUsageService;

    @Test
    public void getGuild_shouldReturnGuild() throws Exception {
        given(discordGuildService.getById(anyLong())).willReturn(new DiscordGuild(0, "TestGuild", "TestUrl"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/guild/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("0"))
                .andExpect(jsonPath("name").value("TestGuild"))
                .andExpect(jsonPath("iconUrl").value("TestUrl"));

    }

    @Test
    public void getGuild_notFound() throws  Exception {
        given(discordGuildService.getById(anyLong())).willThrow(new DiscordGuildNotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/guild/0"))
                .andExpect(status().isNotFound());
    }
}
