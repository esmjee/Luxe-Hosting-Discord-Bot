package me.esmee.luxehosting.bot;

import me.esmee.luxehosting.commands.Command;
import me.esmee.luxehosting.commands.Commands;
import me.esmee.luxehosting.events.Events;
import me.esmee.luxehosting.intents.Intents;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.File;

public class DiscordBot {

    private final String token;
    private JDABuilder jdaBuilder;
    private JDA jda = null;

    public DiscordBot(String token) {
        this.token = token;
    }

    // Functions
    private void registerEventsListeners() {
        try {
            for (Class<?> eventListener : new Events().eventListeners) {
                System.out.println("Registering " + eventListener.getSimpleName() + "...");
                this.jdaBuilder.addEventListeners(eventListener.newInstance());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void registerSlashCommands() {
        if (this.jda == null) return;

        File dir = new File("./src/main/java/me/esmee/luxehosting/commands/commands");
        File[] directoryListing = dir.listFiles();
        if (directoryListing == null) {
            System.out.println("No files found in /commands directory");
            return;
        }

        for (File child : directoryListing) {
            try {
                Command commandClass = (Command)
                        Class.forName(
                                "me.esmee.luxehosting.commands.commands."
                                        + child.getName().replace(".java", "")
                        ).newInstance();
                Commands.registerCommand(commandClass, jda);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private void enableIntents() {
        for (GatewayIntent gatewayIntent : new Intents().intents) {
            this.jdaBuilder.enableIntents(gatewayIntent);
        }
    }

    private void createDefault() {
        this.jdaBuilder = JDABuilder.createDefault(this.token);
    }

    public void start() {
        this.createDefault();
        this.enableIntents();
        this.registerEventsListeners();

        this.jda = this.jdaBuilder.build();
        this.registerSlashCommands();
    }

}
