package com.twu.refactoring.state;

public class Direction {
    private DirectionState directionState;

    public Direction(char direction) {
        setDirectionState(direction);
    }

    private void setDirectionState(char direction) {
        switch (direction) {
            case 'N':
                this.directionState = new North();
                break;
            case 'S':
                this.directionState = new South();
                break;
            case 'E':
                this.directionState = new East();
                break;
            case 'W':
                this.directionState = new West();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public Direction turnRight() {
        return directionState.turnRight();
    }

    public Direction turnLeft() {
        return directionState.turnLeft();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction1 = (Direction) o;

        if (directionState.direction != direction1.directionState.direction) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) directionState.direction;
    }

    @Override
    public String toString() {
        return "Direction{direction=" + directionState.direction + '}';
    }
}
