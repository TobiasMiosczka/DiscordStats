package com.github.tobiasmiosczka.discordstats.rest;

import com.github.tobiasmiosczka.discordstats.persistence.exception.DiscordUserNotFoundException;
import com.github.tobiasmiosczka.discordstats.persistence.model.DiscordUser;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordUserService;
import com.github.tobiasmiosczka.discordstats.persistence.services.DiscordVoiceChannelUsageService;
import com.github.tobiasmiosczka.discordstats.rest.web.controller.UserController;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscordUserService discordUserService;

    @MockBean
    private DiscordVoiceChannelUsageService discordVoiceChannelUsageService;

    @Test
    public void getUser_shouldReturnGuild() throws Exception {
        given(discordUserService.getById(anyLong())).willReturn(new DiscordUser(0, "TestUser", "TestUrl", false, true, true));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("0"))
                .andExpect(jsonPath("name").value("TestUser"))
                .andExpect(jsonPath("avatarUrl").value("TestUrl"))
                .andExpect(jsonPath("bot").value(false))
                .andExpect(jsonPath("allowTracking").value(true))
                .andExpect(jsonPath("allowPublication").value(true));

    }

    @Test
    public void getUser_notFound() throws  Exception {
        given(discordUserService.getById(anyLong())).willThrow(new DiscordUserNotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/0"))
                .andExpect(status().isNotFound());
    }
}
