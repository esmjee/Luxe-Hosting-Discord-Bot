package me.esmee.luxehosting.commands;

import me.esmee.luxehosting.Main;
import me.esmee.luxehosting.bot.Prefix;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command {

    private final String name;
    private final String description;

    private final boolean isSlashCommand;
    private boolean guildOnly;

    private String[] aliases;
    private Permission[] permissions;
    private OptionData[] options;

    public Command(String name, String description, String... aliases) {
        this.name = name;
        this.description = description;
        this.isSlashCommand = true;
        this.aliases = aliases;
    }

    public Command(String name, String description, boolean isSlashCommand, String... aliases) {
        this.name = name;
        this.description = description;
        this.isSlashCommand = isSlashCommand;
        this.aliases = aliases;
    }

    // Setters
    public void setPermissions(Permission... permissions) {
        this.permissions = permissions;
    }

    public void setAliases(String... aliases) {
        this.aliases = aliases;
    }

    public void setOptions(OptionData... options) {
        this.options = options;
    }

    public void setGuildOnly() {
        this.guildOnly = true;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getFullName() {
        String prefix = isSlashCommand ? "/" : Prefix.getPrefix();
        return prefix + getName();
    }

    public String getDescription() {
        return description;
    }

    public boolean isSlashCommand() {
        return isSlashCommand;
    }

    public String[] getAliases() {
        return aliases;
    }

    public boolean isGuildOnly() {
        if (Main.DEBUG) {
            // If debug mode is enabled, only allow guilds to execute slash commands.
            // The API will take a long time to reload the slash commands otherwise.
            return true;
        }

        return guildOnly;
    }

    public List<String> getCommandUsages() {
        List<String> usages = new ArrayList<>();

        usages.add(getName());
        usages.addAll(Arrays.asList(getAliases()));

        return usages;
    }

    public OptionData[] getOptions() {
        return options;
    }

    public Permission[] getPermissions() {
        return permissions;
    }

    // Functions
    public void onSlashCommand(SlashCommandInteractionEvent e) {
    }

    public void onCommand(MessageReceivedEvent e) {
    }

}
