package com.twu.refactoring.state;

public enum DirectionType {

    NORTH("WEST", "EAST"),
    EAST("NORTH", "SOUTH"),
    WEST("SOUTH", "NORTH"),
    SOUTH("EAST", "WEST");

    private String leftDirection;
    private String rightDirection;

    DirectionType(String leftDirection, String rightDirection) {
        this.leftDirection = leftDirection;
        this.rightDirection = rightDirection;
    }

    public String getRightDirection() {
        return rightDirection;
    }

    public String getLeftDirection() {
        return leftDirection;
    }
}
