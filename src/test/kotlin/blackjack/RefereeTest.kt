package blackjack

import Player
import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.card.Suit
import blackjack.model.game.Referee
import blackjack.model.game.Result
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RefereeTest {
    @Test
    fun `Referee 생성 확인`() {
        val dealerHand = Hand(mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES)))
        val playerHand = Hand(mutableListOf(Card(Denomination.ACE, Suit.SPADES), Card(Denomination.SIX, Suit.HEARTS)))
        val player = Player("하디", playerHand)
        assertThat(Referee(Dealer(dealerHand), PlayerEntry(listOf(Player("하디", playerHand)))).dealer.hand).isEqualTo(
            dealerHand,
        )
        assertThat(
            Referee(Dealer(dealerHand), PlayerEntry(listOf(player))).playerEntry.players,
        ).isEqualTo(listOf(player))
    }

    @Test
    fun `플레이어 승리 시나리오`() {
        val dealer =
            Dealer(Hand(mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES))))
        val player =
            Player("채드", Hand(mutableListOf(Card(Denomination.ACE, Suit.SPADES), Card(Denomination.NINE, Suit.HEARTS))))
        dealer.hand.totalScore = 17
        player.hand.totalScore = 20
        val players = listOf(player)
        val playerEntry = PlayerEntry(players)
        val referee = Referee(dealer, playerEntry)
        val results = referee.makeResults()
        assertThat(results).isEqualTo(listOf(Result.PLAYER_WIN))
    }

    @Test
    fun `딜러 승리 시나리오`() {
        val dealer =
            Dealer(Hand(mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.JACK, Suit.SPADES))))
        val player =
            Player("채드", Hand(mutableListOf(Card(Denomination.ACE, Suit.SPADES), Card(Denomination.NINE, Suit.HEARTS))))
        dealer.hand.totalScore = 21
        player.hand.totalScore = 20
        val players = listOf(player)
        val playerEntry = PlayerEntry(players)
        val referee = Referee(dealer, playerEntry)
        val results = referee.makeResults()
        assertThat(results).isEqualTo(listOf(Result.DEALER_WIN))
    }

    @Test
    fun `무승부 시나리오`() {
        val dealer =
            Dealer(Hand(mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.JACK, Suit.SPADES))))
        val player =
            Player("채드", Hand(mutableListOf(Card(Denomination.ACE, Suit.SPADES), Card(Denomination.JACK, Suit.HEARTS))))
        dealer.hand.totalScore = 21
        player.hand.totalScore = 21
        val players = listOf(player)
        val playerEntry = PlayerEntry(players)
        val referee = Referee(dealer, playerEntry)
        val results = referee.makeResults()
        assertThat(results).isEqualTo(listOf(Result.DRAW))
    }

    @Test
    fun `플레이어 버스트 시나리오`() {
        val dealer =
            Dealer(Hand(mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.JACK, Suit.SPADES))))
        val player =
            Player(
                "채드",
                Hand(
                    mutableListOf(
                        Card(Denomination.KING, Suit.SPADES),
                        Card(Denomination.NINE, Suit.HEARTS),
                        Card(Denomination.SIX, Suit.HEARTS),
                    ),
                ),
            )
        dealer.hand.totalScore = 21
        player.hand.totalScore = 25
        val players = listOf(player)
        val playerEntry = PlayerEntry(players)
        val referee = Referee(dealer, playerEntry)
        val results = referee.makeResults()
        assertThat(results).isEqualTo(listOf(Result.DEALER_WIN))
    }

    @Test
    fun `딜러 버스트 시나리오`() {
        val dealer =
            Dealer(
                Hand(
                    mutableListOf(
                        Card(Denomination.KING, Suit.SPADES),
                        Card(Denomination.NINE, Suit.HEARTS),
                        Card(Denomination.SIX, Suit.HEARTS),
                    ),
                ),
            )
        val player =
            Player(
                "채드",
                Hand(mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.JACK, Suit.SPADES))),
            )
        dealer.hand.totalScore = 25
        player.hand.totalScore = 21
        val players = listOf(player)
        val playerEntry = PlayerEntry(players)
        val referee = Referee(dealer, playerEntry)
        val results = referee.makeResults()
        assertThat(results).isEqualTo(listOf(Result.PLAYER_WIN))
    }
}
