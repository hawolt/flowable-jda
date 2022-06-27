package com.hawolt.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Created: 22/04/2022 13:48
 * Author: Twitter @hawolt
 **/

public interface ISlashCommand {
    String getName();

    long getTextChannel();

    void execute(SlashCommandInteractionEvent event);
}
