package lab3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class TVSetTest {
    public TVSet tv = new TVSet();

    @Test
    @DisplayName("Correct TV initialization")
    public void initializationIsCorrect() {
        assertFalse(tv.isTurnedOn());
        assertEquals(tv.getChannel(), 0);
    }

    @Test
    @DisplayName("TV can be turned on")
    public void canTurnedOn() {
        tv.turnOn();
        assertTrue(tv.isTurnedOn());
    }

    @Nested
    @DisplayName("When TV is turned on:")
    class TVIsTurnedOn
    {
        @BeforeEach
        public void setUp() {
            tv.turnOn();
        }
        @Test
        @DisplayName("Сan select channel in the range [1, 99]")
        public void canSelectChannel() {
            assertTrue(tv.selectChannel(5));
            assertEquals(tv.getChannel(), 5);
            assertTrue(tv.selectChannel(1));
            assertEquals(tv.getChannel(), 1);
            assertTrue(tv.selectChannel(99));
            assertEquals(tv.getChannel(), 99);
        }
        @Test
        @DisplayName("Cannot select channel out of range [1, 99]")
        public void cannotSelectChannel() {
            assertFalse(tv.selectChannel(100));
            assertEquals(tv.getChannel(), 1);
            assertFalse(tv.selectChannel(0));
            assertEquals(tv.getChannel(), 1);
            assertFalse(tv.selectChannel(-1));
            assertEquals(tv.getChannel(), 1);
        }

        @Nested
        @DisplayName("Can select previous channel")
        class CanSelectPreviousChannel {
            @Test
            @DisplayName("Selects channel 1 if the channel is not switched")
            public void canSelectDefaultChannel() {
                assertTrue(tv.selectPreviousChannel());
                assertEquals(tv.getChannel(), 1);
            }

            @Test
            @DisplayName("Selects previous channel if the channel was switched")
            public void canSelectPreviousChannel()
            {
                tv.selectChannel(5);
                tv.selectChannel(11);
                assertTrue(tv.selectPreviousChannel());
                assertEquals(tv.getChannel(), 5);
                assertTrue(tv.selectPreviousChannel());
                assertEquals(tv.getChannel(), 11);
            }

        }
    }

    @Test
    @DisplayName("TV can be turned of")
    public void canTurnedOff() {
        tv.turnOff();
        assertFalse(tv.isTurnedOn());
    }

    @Nested
    @DisplayName("When TV is turned off:")
    class TVIsTurnOff {
        @Test
        @DisplayName("Cannot select channel")
        public void cannotSelectChannel() {
            assertFalse(tv.selectChannel(7));
            assertEquals(tv.getChannel(), 0);
        }
        @Test
        @DisplayName("Cannot select previous channel")
        public void cannotSelectPreviousChannel() {
            assertFalse(tv.selectPreviousChannel());
            assertEquals(tv.getChannel(), 0);
        }
    }

    @Test
    @DisplayName("Returns the last selected channel when TV turns on again")
    public void shouldRestoreLastSelectedChannel() {
        tv.turnOn();
        tv.selectChannel(15);
        tv.turnOff();
        tv.turnOn();
        assertEquals(tv.getChannel(), 15);
    }

}
