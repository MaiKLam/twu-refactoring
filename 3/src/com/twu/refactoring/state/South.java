package com.twu.refactoring.state;

public class South extends DirectionState {
    public South() {
        direction = 'S';
    }

    @Override
    Direction turnRight() {
        return new Direction('W');
    }
}
