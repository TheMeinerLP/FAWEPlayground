package com.fastasyncworldedit.playground.worldedit;

import com.fastasyncworldedit.playground.extents.TrackingExtent;
import com.sk89q.worldedit.event.extent.EditSessionEvent;
import com.sk89q.worldedit.util.eventbus.Subscribe;

public class TrackingEditSessionListener {

    @Subscribe
    public void onTracking(EditSessionEvent event) {
        event.setExtent(new TrackingExtent(event.getExtent()));
    }
}
