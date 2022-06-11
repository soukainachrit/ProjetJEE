package com.gsnotes.bo;

import org.hibernate.Session;

public class ModelAtt {
    private String session;
    private Module module;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
