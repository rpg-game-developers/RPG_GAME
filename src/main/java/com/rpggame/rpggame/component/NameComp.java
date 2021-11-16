package com.rpggame.rpggame.component;

public class NameComp implements Component {
    private String name;

    public NameComp() {
        this.name = new String();
    }

    public NameComp(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Component clone() {
        return new NameComp(name);
    }
}
