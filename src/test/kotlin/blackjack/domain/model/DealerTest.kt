package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer("딜러", Card(Suit.HEART, Rank.FIVE), Card(Suit.HEART, Rank.SIX)) // 11점
    }

    @Test
    fun `플레이어별 승패 여부를 반환한다`() {
        val player1 = Player("A", Card(Suit.SPADE, Rank.TWO), Card(Suit.SPADE, Rank.THREE)) // 5점
        val player2 = Player("B", Card(Suit.SPADE, Rank.FIVE), Card(Suit.SPADE, Rank.SIX)) // 11점
        val player3 = Player("C", Card(Suit.SPADE, Rank.QUEEN), Card(Suit.SPADE, Rank.KING)) // 20점
        val results: Map<Player, Result> = dealer.getPlayerResults(listOf(player1, player2, player3))
        val actual: Map<Player, Result> =
            mapOf(
                player1 to Result.LOSE,
                player2 to Result.PUSH,
                player3 to Result.WIN,
            )
        assertThat(results).isEqualTo(actual)
    }

    @Test
    fun `플레이어별 최종 수익을 반환한다`() {
        val dealer = Dealer("딜러", Card(Suit.HEART, Rank.FIVE), Card(Suit.HEART, Rank.SIX)) // 11점
        val player1 = Player("A", 11111, Card(Suit.SPADE, Rank.TWO), Card(Suit.SPADE, Rank.THREE)) // 5점
        val player2 = Player("B", 0, Card(Suit.SPADE, Rank.FIVE), Card(Suit.SPADE, Rank.SIX)) // 11점
        val player3 = Player("C", 55555, Card(Suit.SPADE, Rank.QUEEN), Card(Suit.SPADE, Rank.KING)) // 20점
        val results: Map<Player, Result> = dealer.getPlayerResults(listOf(player1, player2, player3))
        val profits: Map<Player, Int> = dealer.getPlayersProfits(results)
        val actual: Map<Player, Int> =
            mapOf(
                player1 to -11111,
                player2 to 0,
                player3 to 55555,
            )
        assertThat(profits).isEqualTo(actual)
    }

    @Test
    fun `딜러의 점수가 16 이하면 히트할 수 있다`() {
        dealer = Dealer(Card(Suit.HEART, Rank.SIX), Card(Suit.HEART, Rank.KING))
        assertThat(dealer.canHit()).isTrue()
    }

    @Test
    fun `딜러의 점수가 16 초과면 히트할 수 없다`() {
        dealer = Dealer(Card(Suit.HEART, Rank.QUEEN), Card(Suit.HEART, Rank.KING))
        assertThat(dealer.canHit()).isFalse()
    }
}
