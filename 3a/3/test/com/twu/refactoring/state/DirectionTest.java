package com.twu.refactoring.state;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DirectionTest {

    private Direction north;
    private Direction east;

    @Before
    public void setUp() throws Exception {
        north = new Direction(DirectionType.NORTH);
        east = new Direction(DirectionType.EAST);
    }

    @Test
    public void shouldTurnEastWhenTurnRightFromNorth() {
        Direction east = north.turnRight();
        assertThat(east, is(new Direction(DirectionType.EAST)));
    }

    @Test
    public void shouldTurnWestWhenTurnLeftFromNorth() {
        Direction west = north.turnLeft();
        assertThat(west, is(new Direction(DirectionType.WEST)));
    }

    @Test
    public void shouldTurnNorthWhenTurnLeftFromEast() {
        Direction north = east.turnLeft();
        assertThat(north, is(new Direction(DirectionType.NORTH)));
    }
}
