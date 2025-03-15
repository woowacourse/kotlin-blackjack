package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Rank
import blackjack.domain.model.card.Suit
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer(Card(Suit.HEART, Rank.FIVE), Card(Suit.HEART, Rank.SIX)) // 11점
    }

    @Test
    fun `플레이어별 최종 수익을 반환한다 1`() {
        val dealer = Dealer(Card(Suit.DIAMOND, Rank.THREE), Card(Suit.CLUB, Rank.NINE), Card(Suit.DIAMOND, Rank.EIGHT)) // 20점
        val player1 = Player("pobi", 10000, Card(Suit.HEART, Rank.TWO), Card(Suit.SPADE, Rank.EIGHT), Card(Suit.CLUB, Rank.ACE)) // 21점
        val player2 = Player("jason", 20000, Card(Suit.CLUB, Rank.SEVEN), Card(Suit.SPADE, Rank.KING)) // 17점
        val scoreboard = Scoreboard(dealer, listOf(player1, player2))
        val playersProfits: Map<Player, Int> = scoreboard.playersProfits()
        val actual: Map<Player, Int> =
            mapOf(
                player1 to 10000,
                player2 to -20000,
            )
        assertThat(playersProfits).isEqualTo(actual)
    }

    @Test
    fun `플레이어별 최종 수익을 반환한다 2`() {
        val dealer = Dealer(Card(Suit.HEART, Rank.FIVE), Card(Suit.HEART, Rank.SIX)) // 11점
        val player1 = Player("A", 11111, Card(Suit.SPADE, Rank.TWO), Card(Suit.SPADE, Rank.THREE)) // 5점
        val player2 = Player("B", 0, Card(Suit.SPADE, Rank.FIVE), Card(Suit.SPADE, Rank.SIX)) // 11점
        val player3 = Player("C", 33333, Card(Suit.SPADE, Rank.QUEEN), Card(Suit.SPADE, Rank.KING)) // 20점
        val player4 = Player("D", 55555, Card(Suit.SPADE, Rank.ACE), Card(Suit.SPADE, Rank.KING)) // 21점
        val scoreboard = Scoreboard(dealer, listOf(player1, player2, player3, player4))
        val playersProfits: Map<Player, Int> = scoreboard.playersProfits()
        val actual: Map<Player, Int> =
            mapOf(
                player1 to -11111,
                player2 to 0,
                player3 to 33333,
                player4 to 27778,
            )
        assertThat(playersProfits).isEqualTo(actual)
    }

    @Test
    fun `딜러의 최종 수익을 반환한다 1`() {
        val dealer = Dealer(Card(Suit.DIAMOND, Rank.THREE), Card(Suit.CLUB, Rank.NINE), Card(Suit.DIAMOND, Rank.EIGHT)) // 20점
        val player1 = Player("pobi", 10000, Card(Suit.HEART, Rank.TWO), Card(Suit.SPADE, Rank.EIGHT), Card(Suit.CLUB, Rank.ACE)) // 21점
        val player2 = Player("jason", 20000, Card(Suit.CLUB, Rank.SEVEN), Card(Suit.SPADE, Rank.KING)) // 17점
        val scoreboard = Scoreboard(dealer, listOf(player1, player2))
        val dealerProfit: Int = scoreboard.dealerProfit(scoreboard.playersProfits())
        val actual: Int = -10000 + 20000
        assertThat(dealerProfit).isEqualTo(actual)
    }

    @Test
    fun `딜러의 최종 수익을 반환한다 2`() {
        val dealer = Dealer(Card(Suit.HEART, Rank.FIVE), Card(Suit.HEART, Rank.SIX)) // 11점
        val player1 = Player("A", 11111, Card(Suit.SPADE, Rank.TWO), Card(Suit.SPADE, Rank.THREE)) // 5점
        val player2 = Player("B", 0, Card(Suit.SPADE, Rank.FIVE), Card(Suit.SPADE, Rank.SIX)) // 11점
        val player3 = Player("C", 33333, Card(Suit.SPADE, Rank.QUEEN), Card(Suit.SPADE, Rank.KING)) // 20점
        val player4 = Player("D", 55555, Card(Suit.SPADE, Rank.ACE), Card(Suit.SPADE, Rank.KING)) // 21점
        val scoreboard = Scoreboard(dealer, listOf(player1, player2, player3, player4))
        val dealerProfit: Int = scoreboard.dealerProfit(scoreboard.playersProfits())
        val actual: Int = 11111 + 0 - 33333 - 27778
        assertThat(dealerProfit).isEqualTo(actual)
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
