package com.hawolt.commands;

import com.hawolt.routines.IRoutine;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created: 22/04/2022 13:13
 * Author: Twitter @hawolt
 **/

public class SlashCommandHandler implements IRoutine<SlashCommandInteractionEvent> {

    private final Map<String, ISlashCommand> map = new HashMap<>();

    public void add(ISlashCommand command) {
        map.put(command.getName(), command);
    }

    @Override
    public void apply(SlashCommandInteractionEvent event) {
        if (!map.containsKey(event.getName())) return;
        ISlashCommand command = map.get(event.getName());
        if (command.getTextChannel() != 0 && event.getTextChannel().getIdLong() != command.getTextChannel()) return;
        command.execute(event);
    }
}
