package me.esmee.luxehosting.intents;

import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.ArrayList;
import java.util.List;

public class Intents {

    public List<GatewayIntent> intents = new ArrayList<>();

    public Intents() {
        this.registerIntents();
    }

    private void registerIntents() {
        this.intents.add(GatewayIntent.MESSAGE_CONTENT);
        this.intents.add(GatewayIntent.GUILD_MESSAGES);
        this.intents.add(GatewayIntent.GUILD_MEMBERS);
    }

}
