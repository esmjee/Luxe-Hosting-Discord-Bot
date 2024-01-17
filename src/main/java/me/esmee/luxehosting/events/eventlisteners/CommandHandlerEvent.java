package me.esmee.luxehosting.events.eventlisteners;

import me.esmee.luxehosting.commands.Command;
import me.esmee.luxehosting.commands.Commands;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandHandlerEvent extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        super.onMessageReceived(e);

        if (e.getAuthor().isBot()) return;

        String commandName = e.getMessage().getContentRaw().split(" ")[0];

        for (Command command : Commands.getCommands()) {
            if (!command.getCommandUsages().contains(commandName)) continue;

            command.onCommand(e);
            break;
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        super.onSlashCommandInteraction(e);

        for (Command command : Commands.getSlashCommands()) {
            if (!command.getCommandUsages().contains(e.getName())) continue;

            command.onSlashCommand(e);
            break;
        }
    }

}
