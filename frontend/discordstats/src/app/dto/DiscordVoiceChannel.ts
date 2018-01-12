import { DiscordGuild } from "./DiscordGuild";

export interface DiscordVoiceChannel {
    id: string;
    discordGuild: DiscordGuild;
    name: string;
    position: number;
}