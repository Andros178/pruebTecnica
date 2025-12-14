package com.example.generator.model;

public class IdDef {

    private String name;
    private String type;
    private String column;
    private SequenceDef sequence;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getColumn() {
        return column;
    }

    public SequenceDef getSequence() {
        return sequence;
    }
}
