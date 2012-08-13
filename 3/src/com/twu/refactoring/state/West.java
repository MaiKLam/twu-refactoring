package com.twu.refactoring.state;

public class West extends DirectionState {
    public West() {
        direction = 'W';
    }

    @Override
    Direction turnRight() {
        return new Direction('S');
    }
}

