package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer("동전", cards = listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `플레이어별 승패 여부를 반환한다`() {
        val player1 = Player("A", listOf(Card(Suit.HEART, Rank.TWO))) // 2점
        val player2 = Player("B", listOf(Card(Suit.HEART, Rank.ACE))) // 11점
        val player3 = Player("C", listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING))) // 21점
        val verdicts: Map<Player, Verdict> = dealer.getPlayerVerdict(listOf(player1, player2, player3))
        val actual: Map<Player, Verdict> =
            mapOf(
                player1 to Verdict.LOSE,
                player2 to Verdict.DRAW,
                player3 to Verdict.WIN,
            )
        assertThat(verdicts).isEqualTo(actual)
    }

    @Test
    fun `딜러의 승패 횟수를 반환한다`() {
        val player1 = Player("A", listOf(Card(Suit.HEART, Rank.TWO))) // 2점
        val player2 = Player("B", listOf(Card(Suit.HEART, Rank.ACE))) // 11점
        val player3 = Player("C", listOf(Card(Suit.HEART, Rank.ACE))) // 11점
        val player4 = Player("D", listOf(Card(Suit.HEART, Rank.ACE))) // 11점
        val player5 = Player("E", listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING))) // 21점
        val player6 = Player("F", listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING))) // 21점
        val verdicts: Map<Verdict, Int> = dealer.getDealerVerdicts(listOf(player1, player2, player3, player4, player5, player6))
        val actual: Map<Verdict, Int> =
            mapOf(
                Verdict.WIN to 1,
                Verdict.LOSE to 2,
                Verdict.DRAW to 3,
            )
        assertThat(verdicts).isEqualTo(actual)
    }
}
