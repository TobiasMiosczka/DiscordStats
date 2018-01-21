import { DiscordVoiceChannel } from "./DiscordVoiceChannel";
import { DiscordUser } from "./DiscordUser";

export interface DiscordVoiceChannelUsage {
    discordUser: DiscordUser;
    discordVoiceChannel: DiscordVoiceChannel;
    dateFrom: Date;
    dateTo: Date;
    duration: number;
}