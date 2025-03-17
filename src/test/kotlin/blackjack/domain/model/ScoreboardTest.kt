package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Rank
import blackjack.domain.model.card.Suit
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import blackjack.domain.model.result.Scoreboard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreboardTest {
    @Test
    fun `플레이어별 최종 수익을 반환한다 1`() {
        val dealer = Dealer(Card(Suit.DIAMOND, Rank.THREE), Card(Suit.CLUB, Rank.NINE), Card(Suit.DIAMOND, Rank.EIGHT)) // 20점
        val player1 = Player("pobi", 10000, Card(Suit.HEART, Rank.TWO), Card(Suit.SPADE, Rank.EIGHT), Card(Suit.CLUB, Rank.ACE)) // 21점
        val player2 = Player("jason", 20000, Card(Suit.CLUB, Rank.SEVEN), Card(Suit.SPADE, Rank.KING)) // 17점
        val scoreboard = Scoreboard(dealer, listOf(player1, player2))
        val playersProfits: Map<Player, Money> = scoreboard.playersProfits()
        val actual: Map<Player, Money> =
            mapOf(
                player1 to Money(10000),
                player2 to Money(-20000),
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
        val playersProfits: Map<Player, Money> = scoreboard.playersProfits()
        val actual: Map<Player, Money> =
            mapOf(
                player1 to Money(-11111),
                player2 to Money(0),
                player3 to Money(33333),
                player4 to Money(27777),
            )
        assertThat(playersProfits).isEqualTo(actual)
    }

    @Test
    fun `딜러의 최종 수익을 반환한다 1`() {
        val dealer = Dealer(Card(Suit.DIAMOND, Rank.THREE), Card(Suit.CLUB, Rank.NINE), Card(Suit.DIAMOND, Rank.EIGHT)) // 20점
        val player1 = Player("pobi", 10000, Card(Suit.HEART, Rank.TWO), Card(Suit.SPADE, Rank.EIGHT), Card(Suit.CLUB, Rank.ACE)) // 21점
        val player2 = Player("jason", 20000, Card(Suit.CLUB, Rank.SEVEN), Card(Suit.SPADE, Rank.KING)) // 17점
        val scoreboard = Scoreboard(dealer, listOf(player1, player2))
        val dealerProfit: Money = scoreboard.dealerProfit(scoreboard.players)
        val actual = Money(-10000 + 20000)
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
        val dealerProfit: Money = scoreboard.dealerProfit(scoreboard.players)
        val actual = Money(11111 + 0 - 33333 - 27777)
        assertThat(dealerProfit).isEqualTo(actual)
    }
}
