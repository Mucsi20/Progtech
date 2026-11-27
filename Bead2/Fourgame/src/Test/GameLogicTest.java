package Test;

import Model.GameLogic;
import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {
    private Player player1;
    private Player player2;
    private GameLogic gameLogic;

    @BeforeEach
    void setUp() {
        player1 = new Player("Player 1", Color.RED);
        player2 = new Player("Player 2", Color.BLUE);
        gameLogic = new GameLogic(3, new Player[]{player1, player2});
    }

    @Test
    void initialStateHasZeroValuesUnownedCellsAndPlayer1Turn() {
        assertEquals(0, gameLogic.getCurrentPlayerIndex());
        assertEquals(0, player1.getScore());
        assertEquals(0, player2.getScore());

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertEquals(0, gameLogic.getCellValue(r, c));
                assertEquals(-1, gameLogic.getCellOwner(r, c));
            }
        }
    }

    @Test
    void clickCenterCellIncrementsCrossAndLeavesCornersUnchanged() {
        gameLogic.clickCell(1, 1);

        // Clicked cell and orthogonal neighbors incremented
        assertEquals(1, gameLogic.getCellValue(1, 1));
        assertEquals(1, gameLogic.getCellValue(0, 1));
        assertEquals(1, gameLogic.getCellValue(2, 1));
        assertEquals(1, gameLogic.getCellValue(1, 0));
        assertEquals(1, gameLogic.getCellValue(1, 2));

        // Diagonal corners untouched
        assertEquals(0, gameLogic.getCellValue(0, 0));
        assertEquals(0, gameLogic.getCellValue(0, 2));
        assertEquals(0, gameLogic.getCellValue(2, 0));
        assertEquals(0, gameLogic.getCellValue(2, 2));
    }

    @Test
    void clickCornerCellStaysWithinBounds() {
        assertDoesNotThrow(() -> gameLogic.clickCell(0, 0));

        assertEquals(1, gameLogic.getCellValue(0, 0));
        assertEquals(1, gameLogic.getCellValue(1, 0));
        assertEquals(1, gameLogic.getCellValue(0, 1));
        assertEquals(0, gameLogic.getCellValue(1, 1));
    }

    @Test
    void clickEdgeCellStaysWithinBounds() {
        assertDoesNotThrow(() -> gameLogic.clickCell(0, 1));

        assertEquals(1, gameLogic.getCellValue(0, 1));
        assertEquals(1, gameLogic.getCellValue(0, 0));
        assertEquals(1, gameLogic.getCellValue(0, 2));
        assertEquals(1, gameLogic.getCellValue(1, 1));
        assertEquals(0, gameLogic.getCellValue(2, 1));
    }

    @Test
    void turnsAlternateAfterEachMove() {
        assertEquals(0, gameLogic.getCurrentPlayerIndex());

        gameLogic.clickCell(0, 0);
        assertEquals(1, gameLogic.getCurrentPlayerIndex());

        gameLogic.clickCell(2, 2);
        assertEquals(0, gameLogic.getCurrentPlayerIndex());
    }

    @Test
    void reachingFourAssignsOwnershipAndIncrementsActivePlayerScore() {
        // 4 clicks on (1, 1): turns are P1 (0), P2 (1), P1 (0), P2 (1)
        // On the 4th click (Player 2's turn), all 5 cells in the cross reach 4
        gameLogic.clickCell(1, 1);
        gameLogic.clickCell(1, 1);
        gameLogic.clickCell(1, 1);
        gameLogic.clickCell(1, 1);

        assertEquals(4, gameLogic.getCellValue(1, 1));
        assertEquals(1, gameLogic.getCellOwner(1, 1));
        assertEquals(1, gameLogic.getCellOwner(0, 1));
        assertEquals(1, gameLogic.getCellOwner(2, 1));
        assertEquals(1, gameLogic.getCellOwner(1, 0));
        assertEquals(1, gameLogic.getCellOwner(1, 2));

        assertEquals(0, player1.getScore());
        assertEquals(5, player2.getScore());
    }

    @Test
    void cellValueCapsAtFourAndDoesNotChangeOwnerOrScore() {
        for (int i = 0; i < 4; i++) {
            gameLogic.clickCell(0, 0);
        }

        // Player 2 (index 1) claimed (0,0), (1,0), and (0,1)
        assertEquals(3, player2.getScore());
        assertEquals(0, gameLogic.getCurrentPlayerIndex());

        // 5th click by Player 1 should not raise values above 4 or steal ownership
        gameLogic.clickCell(0, 0);

        assertEquals(4, gameLogic.getCellValue(0, 0));
        assertEquals(1, gameLogic.getCellOwner(0, 0));
        assertEquals(0, player1.getScore());
        assertEquals(3, player2.getScore());
    }

    @Test
    void increaseScoreCreditsCurrentPlayer() {
        gameLogic.increaseScore();
        assertEquals(1, player1.getScore());
        assertEquals(0, player2.getScore());

        gameLogic.clickCell(0, 0); // switch to Player 2
        gameLogic.increaseScore();
        assertEquals(1, player1.getScore());
        assertEquals(1, player2.getScore());
    }

    @Test
    void fullGameStopsTurnSwitchingAndDeterminesPlayer1AsWinner() {
        GameLogic smallGame = new GameLogic(2, new Player[]{player1, player2});

        smallGame.clickCell(0, 0); // P1: (0,0)=1, (0,1)=1, (1,0)=1
        smallGame.clickCell(0, 1); // P2: (0,0)=2, (0,1)=2, (1,1)=1
        smallGame.clickCell(1, 0); // P1: (0,0)=3, (1,0)=2, (1,1)=2
        smallGame.clickCell(1, 1); // P2: (0,1)=3, (1,0)=3, (1,1)=3
        smallGame.clickCell(0, 0); // P1 claims (0,0), (0,1), (1,0) -> P1 score = 3
        smallGame.clickCell(1, 1); // P2 claims (1,1) -> P2 score = 1, board full

        assertEquals(3, player1.getScore());
        assertEquals(1, player2.getScore());
        assertEquals(1, smallGame.getCurrentPlayerIndex()); // turn does not switch once all cells are owned
        assertEquals(player1, smallGame.getWinner());
    }

    @Test
    void getWinnerReturnsPlayer2WhenPlayer2HasHigherScore() {
        GameLogic singleCellGame = new GameLogic(1, new Player[]{player1, player2});

        singleCellGame.clickCell(0, 0); // P1 -> 1
        singleCellGame.clickCell(0, 0); // P2 -> 2
        singleCellGame.clickCell(0, 0); // P1 -> 3
        singleCellGame.clickCell(0, 0); // P2 -> 4 (claims cell, board full)

        assertEquals(0, player1.getScore());
        assertEquals(1, player2.getScore());
        assertEquals(1, singleCellGame.getCurrentPlayerIndex());
        assertEquals(player2, singleCellGame.getWinner());
    }
}