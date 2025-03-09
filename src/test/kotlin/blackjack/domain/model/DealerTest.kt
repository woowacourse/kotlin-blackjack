package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer("동전", listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `플레이어별 승패 여부를 반환한다`() {
        val player1 = Player("A", listOf(Card(Suit.HEART, Rank.TWO))) //  2점
        val player2 = Player("B", listOf(Card(Suit.HEART, Rank.ACE))) // 11점
        val player3 = Player("C", listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING))) // 21점
        val verdicts: Map<Player, Result> = dealer.getPlayerResult(listOf(player1, player2, player3))
        val actual: Map<Player, Result> =
            mapOf(
                player1 to Result.LOSE,
                player2 to Result.DRAW,
                player3 to Result.WIN,
            )
        assertThat(verdicts).isEqualTo(actual)
    }

    @Test
    fun `딜러의 승패 횟수를 반환한다`() {
        val player1 = Player("A", listOf(Card(Suit.HEART, Rank.TWO))) //  2점
        val player2 = Player("B", listOf(Card(Suit.HEART, Rank.ACE))) // 11점
        val player3 = Player("C", listOf(Card(Suit.HEART, Rank.ACE))) // 11점
        val player4 = Player("D", listOf(Card(Suit.HEART, Rank.ACE))) // 11점
        val player5 = Player("E", listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING))) // 21점
        val player6 = Player("F", listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING))) // 21점
        val players = listOf(player1, player2, player3, player4, player5, player6)
        val playerVerdicts: Map<Player, Result> = dealer.getPlayerResult(players)
        val verdicts: Map<Result, Int> = dealer.getDealerResults(playerVerdicts)
        val actual: Map<Result, Int> = mapOf(Result.WIN to 1, Result.LOSE to 2, Result.DRAW to 3)
        assertThat(verdicts).isEqualTo(actual)
    }
}
