package me.esmee.luxehosting.commands.commands;

import me.esmee.luxehosting.commands.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping", "Pong!", "ms");
        this.setGuildOnly();
    }

    @Override
    public void onSlashCommand(SlashCommandInteractionEvent e) {
        e.reply("Websocket Ping: " + e.getJDA().getGatewayPing() + "ms").queue();
    }

}
