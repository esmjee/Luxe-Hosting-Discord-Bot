package me.esmee.luxehosting.commands.commands;

import me.esmee.luxehosting.commands.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class LinkCommand extends Command {

    public LinkCommand() {
        super("link", "Link your Discord account to your Luxe Hosting account");

        OptionData email = new OptionData(OptionType.STRING, "email", "Your Luxe Hosting email address")
                .setRequired(true);

        this.setOptions(email);
    }

    @Override
    public void onSlashCommand(SlashCommandInteractionEvent e) { // WIP
//        OptionMapping emailOption = e.getOption("email");
//        if (emailOption == null) {
//            e.reply("Please provide your email address").queue();
//            return;
//        }
//
//        String email = emailOption.getAsString();
//        e.reply("Your email is " + email).queue();
    }

}
