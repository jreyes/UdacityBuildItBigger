package com.vaporwarecorp.jokester.server;

public class JokeBean {
// ------------------------------ FIELDS ------------------------------

    private final String data;

// --------------------------- CONSTRUCTORS ---------------------------

    public JokeBean(String joke) {
        this.data = joke;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public String getData() {
        return data;
    }
}