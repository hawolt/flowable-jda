package com.hawolt.routines;

import net.dv8tion.jda.api.events.GenericEvent;

/**
 * Created: 22/04/2022 12:53
 * Author: Twitter @hawolt
 **/

public interface IRoutine<T extends GenericEvent> {

    void apply(T event);
}
