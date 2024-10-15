package com.rosed.elemental;

import com.rosed.elemental.Enums.Element;

public class ElementalPlayer {

    private Element element;

    public ElementalPlayer() { element = null; }
    public ElementalPlayer(Element element) { this.element = element; }

    public void setElement(Element element) { this.element = element; }
    public Element getElement() { return element; }
}
