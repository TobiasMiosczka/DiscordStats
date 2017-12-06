package com.github.tobiasmiosczka.discordstats.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordDto {
    @NotNull
    @Size(min = 6, max = 60)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
