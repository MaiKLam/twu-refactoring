package com.twu.refactoring.state;

public class East extends DirectionState {
    public East(){
        direction = 'E';
    }

    @Override
    Direction turnRight() {
        return new Direction('N');
    }
}
