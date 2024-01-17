package me.esmee.luxehosting.commands;

import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.List;

public class Commands {

    private static final List<Command> slash_commands = new ArrayList<>();
    private static final List<Command> commands = new ArrayList<>();

    public static List<Command> getCommands() {
        return commands;
    }

    public static List<Command> getSlashCommands() {
        return slash_commands;
    }

    public static void registerCommand(Command command, JDA jda) {
        if (!command.isSlashCommand()) {
            commands.add(command);
            return;
        }

        slash_commands.add(command);
        if (command.getOptions() == null) {
            registerCommandWithoutOptions(jda, command);
            return;
        }

        registerCommandWithOptions(jda, command);
    }

    private static void registerCommandWithOptions(JDA jda, Command command) {
        jda.upsertCommand(command.getName(), command.getDescription())
                .addOptions(command.getOptions())
                .setGuildOnly(command.isGuildOnly())
                .queue();

        System.out.println("Registered command " + command.getFullName() + " with " + command.getOptions().length + " options.");

        for (String alias : command.getAliases()) {
            jda.upsertCommand(alias, command.getDescription())
                    .addOptions(command.getOptions())
                    .setGuildOnly(command.isGuildOnly())
                    .queue();

            System.out.println("Registered alias '" + alias + "' for " + command.getFullName() + " with " + command.getOptions().length + " options.");
        }
    }

    private static void registerCommandWithoutOptions(JDA jda, Command command) {
        jda.upsertCommand(command.getName(), command.getDescription())
                .setGuildOnly(command.isGuildOnly())
                .queue();

        System.out.println("Registered command " + command.getFullName() + " without options.");

        for (String alias : command.getAliases()) {
            jda.upsertCommand(alias, command.getDescription())
                    .setGuildOnly(command.isGuildOnly())
                    .queue();

            System.out.println("Registered alias '" + alias + "' for " + command.getFullName() + " without options.");
        }
    }

}
