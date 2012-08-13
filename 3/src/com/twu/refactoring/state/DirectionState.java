package com.twu.refactoring.state;

public abstract class DirectionState {

    protected char direction;

    abstract Direction turnRight();

    Direction turnLeft() {
        switch (direction) {
            case 'N':
                return new Direction('W');
            case 'S':
                return new Direction('E');
            case 'E':
                return new Direction('N');
            case 'W':
                return new Direction('S');
            default:
                throw new IllegalArgumentException();
        }
    }
}
