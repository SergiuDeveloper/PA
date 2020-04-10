package com.management.document;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public abstract class Document implements Serializable {
    public Document() {
        this.tags = new HashMap<>();
    }

    protected int id;
    public int getID() {
        return this.id;
    }
    protected void setID(int id) {
        this.id = id;
    }

    protected String name;
    public String getName() {
        return this.name;
    }
    protected void setName(String name) {
        this.name = name;
    }

    protected Map<String, String> tags;
    public Map<String, String> getTags() {
        return this.tags;
    }
    protected void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    protected String path;
    public String getPath() {
        return this.path;
    }
    protected void setPath(String path) {
        this.path = path;
    }
}
