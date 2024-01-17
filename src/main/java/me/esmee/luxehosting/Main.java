package me.esmee.luxehosting;

import me.esmee.luxehosting.bot.DiscordBot;
import me.esmee.luxehosting.bot.Prefix;
import me.esmee.luxehosting.config.Config;

public class Main {

    public static final boolean DEBUG = true;

    public static void main(String[] args) {
        String token = Config.get("discord_token");
        String prefix = Config.get("prefix");
        if (token == null || prefix == null) {
            System.out.println("Bot could not be initialized!");
            return;
        }

        Prefix.setPrefix(prefix);

        DiscordBot discordBot = new DiscordBot(token);
        discordBot.start();

        System.out.println("Bot has been initialized in " + (DEBUG ? "debug" : "production") + " mode!");
    }

}