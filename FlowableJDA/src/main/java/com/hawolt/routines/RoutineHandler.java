package com.hawolt.routines;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created: 22/04/2022 12:52
 * Author: Twitter @hawolt
 **/

public class RoutineHandler extends ListenerAdapter {

    private final Map<Class<? extends GenericEvent>, List<IRoutine<? extends GenericEvent>>> routines = new HashMap<>();
    private final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void addRoutine(Class<? extends GenericEvent> event, IRoutine<? extends GenericEvent> routine) {
        if (!routines.containsKey(event)) routines.put(event, new ArrayList<>());
        routines.get(event).add(routine);
    }

    @Override
    public void onGenericEvent(@Nonnull GenericEvent event) {
        List<IRoutine<? extends GenericEvent>> list = routines.get(event.getClass());
        if (list != null) {
            list.forEach(routine -> service.execute(() -> routine.apply(uncheckedCast(event))));
        }
    }

    @SuppressWarnings("all")
    private <T> T uncheckedCast(Object type) {
        return (T) type;
    }

}
