package ru.netology.house;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {
    Game service = new Game();

    Player player1 = new Player(1, "Санек", 2);
    Player player2 = new Player(2, "Ванек", 2);
    Player player3 = new Player(3, "Димон", 4);
    Player player4 = new Player(4, "Гошан", 3);

    @Test
    public void shouldRegisterOnePlayer() {
        service.register(player3);

        boolean expected = true;
        boolean actual = service.getPlayers().contains(player3);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldAddSeveralPlayers() {

        service.register(player1);
        service.register(player2);
        service.register(player3);
        service.register(player4);

        List<Player> expected = Arrays.asList(new Player[]{player1, player2, player3, player4});
        ArrayList<Player> actual = service.getPlayers();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldWinFirstPlayer() {

        service.register(player1);
        service.register(player3);

        int expected = 1;
        int actual = service.round("Димон", "Санек");

        assertEquals(expected, actual);

    }

    @Test
    public void shouldWinSecondPlayer() {

        service.register(player1);
        service.register(player4);

        int expected = 2;
        int actual = service.round("Санек", "Гошан");

        assertEquals(expected, actual);

    }

    @Test
    public void shouldNobodyWin() {

        service.register(player1);
        service.register(player2);

        int expected = 0;
        int actual = service.round("Санек", "Ванек");

        assertEquals(expected, actual);

    }

    @Test
    public void shouldThrowNotRegisteredExceptionForTheFirstPlayer() {
        service.register(player1);

        assertThrows(NotRegisteredException.class, () -> {
            service.round("Люська", "Санек");
        });
    }

    @Test
    public void shouldThrowNotRegisteredExceptionForTheSecondPlayer() {
        service.register(player1);

        assertThrows(NotRegisteredException.class, () -> {
            service.round("Санек", "Люська");
        });
    }

    @Test
    public void shouldThrowNotRegisteredExceptionForBothPlayers() {

        assertThrows(NotRegisteredException.class, () -> {
            service.round("Гошан", "Димон");
        });
    }


}
