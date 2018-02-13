package com.github.tobiasmiosczka.discordstats.rest;

import com.github.tobiasmiosczka.discordstats.persistence.exception.DiscordVoiceChannelNotFoundException;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordGuild;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordVoiceChannel;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordGuildService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelUsageService;
import com.github.tobiasmiosczka.discordstats.rest.web.controller.VoiceChannelController;
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
@WebMvcTest(VoiceChannelController.class)
public class VoiceChannelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscordGuildService discordGuildService;

    @MockBean
    private DiscordVoiceChannelService discordVoiceChannelService;

    @MockBean
    private DiscordVoiceChannelUsageService discordVoiceChannelUsageService;

    @Test
    public void getVoiceChannel_shouldReturnVoiceChannel() throws Exception {
        DiscordGuild mockDioscordGuild = new DiscordGuild(0, "TestGuild", "TestUrl");
        DiscordVoiceChannel mockDiscordVoiceChannel = new DiscordVoiceChannel(0, mockDioscordGuild, "TestVoiceChannel", 5, false);
        given(discordVoiceChannelService.getById(anyLong())).willReturn(mockDiscordVoiceChannel);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/voice-channel/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("0"))
                .andExpect(jsonPath("discordGuild").isNotEmpty())
                .andExpect(jsonPath("name").value("TestVoiceChannel"))
                .andExpect(jsonPath("position").value(5))
                .andExpect(jsonPath("deleted").value(false));
    }

    @Test
    public void getVoiceChannel_notFound() throws  Exception {
        given(discordVoiceChannelService.getById(anyLong())).willThrow(new DiscordVoiceChannelNotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/voice-channel/0"))
                .andExpect(status().isNotFound());
    }
}

