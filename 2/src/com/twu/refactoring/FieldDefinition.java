package com.twu.refactoring;

public class FieldDefinition {

    public final int startIndex;
    public final int endIndex;
    public final int lowerBound;
    public final int upperBound;
    public final String name;

    public FieldDefinition(String name, int startIndex, int endIndex, int lowerBound, int upperBound){
        this.name = name;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }


}
