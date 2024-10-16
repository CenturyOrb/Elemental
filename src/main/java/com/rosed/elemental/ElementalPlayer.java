package com.rosed.elemental;

import com.rosed.elemental.Enums.Element;

import java.util.UUID;

public class ElementalPlayer {

    private Element element;
    private UUID uuid;

    public ElementalPlayer(UUID uuid) {
        element = null;
        this.uuid = uuid;
    }

    public ElementalPlayer(UUID uuid, Element element) {
        this.uuid = uuid;
        this.element = element;
    }

    public void setElement(Element element) { this.element = element; }
    public Element getElement() { return element; }

    public void setUuid(UUID uuid) { this.uuid = uuid; }
    public UUID getUuid() { return uuid; }
}
