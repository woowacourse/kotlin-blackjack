import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.card.Suit
import blackjack.model.game.BettingMoney
import blackjack.model.game.Result
import blackjack.model.player.PlayerResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `플레이어의 상태가 무승부라면 수익률은 0이다`() {
        val player =
            Player("Test", Hand(listOf(Card(Denomination.ACE, Suit.HEARTS)).toMutableList()), BettingMoney(1000))
        val playerResult = PlayerResult(player, Result.DRAW)
        assertEquals(0, playerResult.profit)
    }

    @Test
    fun `플레이어의 상태가 승리이고 블랙잭이라면 수익률은 1 5배이다`() {
        val player =
            Player(
                "Test",
                Hand(listOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.JACK, Suit.SPADES)).toMutableList()),
                BettingMoney(1000),
            )
        val playerResult = PlayerResult(player, Result.PLAYER_WIN)
        assertEquals(1500, playerResult.profit)
    }

    @Test
    fun `플레이어의 상태가 패배라면 수익률은 -1000이다`() {
        val player =
            Player("Test", Hand(listOf(Card(Denomination.ACE, Suit.HEARTS)).toMutableList()), BettingMoney(1000))
        val playerResult = PlayerResult(player, Result.DEALER_WIN)
        assertEquals(-1000, playerResult.profit)
    }
}
