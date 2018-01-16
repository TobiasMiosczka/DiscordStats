import { DiscordGuild } from "./DiscordGuild";

export interface DiscordUser {
    id: string;
    name: string;
    avatarUrl: string;
    discordGuilds: Array<DiscordGuild>;
    bot: boolean;
}