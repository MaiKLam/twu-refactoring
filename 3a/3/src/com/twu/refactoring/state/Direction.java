package com.twu.refactoring.state;

public class Direction {
    private final DirectionType direction;

    public Direction(DirectionType direction) {
        this.direction = direction;
    }

    public Direction turnRight() {
        String rightDirection = direction.getRightDirection();
        return new Direction(DirectionType.valueOf(rightDirection));
    }

    public Direction turnLeft() {
        String leftDirection = direction.getLeftDirection();
        return new Direction(DirectionType.valueOf(leftDirection));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction1 = (Direction) o;

        if (direction != direction1.direction) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return direction != null ? direction.hashCode() : 0;
    }
}
