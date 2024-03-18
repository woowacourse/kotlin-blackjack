import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.card.Suit
import blackjack.model.game.BettingMoney
import blackjack.model.game.GameResult
import blackjack.model.game.Result
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameResultTest {
    private val dealer = Dealer(Hand(listOf(Card(Denomination.TWO, Suit.CLUBS)).toMutableList()))

    @Test
    fun `플레이어의 상태가 무승부라면 수익률은 0이다`() {
        val player =
            Player("Test", Hand(listOf(Card(Denomination.ACE, Suit.HEARTS)).toMutableList()), BettingMoney(1000))
        val playerResult = PlayerResult(player, Result.DRAW)
        val gameResult = GameResult(dealer, listOf(playerResult))
        assertEquals(0, gameResult.playerProfits[player])
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
        val gameResult = GameResult(dealer, listOf(playerResult))
        val expectedProfit = (1500).toInt()
        assertEquals(expectedProfit, gameResult.playerProfits[player])
    }

    @Test
    fun `플레이어의 상태가 패배라면 수익률은 -1000이다`() {
        val player =
            Player("Test", Hand(listOf(Card(Denomination.ACE, Suit.HEARTS)).toMutableList()), BettingMoney(1000))
        val playerResult = PlayerResult(player, Result.DEALER_WIN)
        val gameResult = GameResult(dealer, listOf(playerResult))
        assertEquals(-1000, gameResult.playerProfits[player])
    }
}
