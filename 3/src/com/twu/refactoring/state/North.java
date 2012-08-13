package com.twu.refactoring.state;

public class North extends DirectionState {
    public North() {
        direction = 'N';
    }

    @Override
    Direction turnRight() {
        return new Direction('E');
    }
}
