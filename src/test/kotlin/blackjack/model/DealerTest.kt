package blackjack.model

import model.Card
import model.Cards
import model.Dealer
import model.GameResult
import model.Name
import model.Participants
import model.Player
import model.Players
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 처음 보여지는 카드는 한 장이다`() {
        val dealer = Dealer(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.CLOVER))
        val expected = setOf(Card(Rank.ACE, Suit.SPADE))
        assertThat(dealer.getFirstOpenCards().cards).isEqualTo(expected)
    }

    @Test
    fun `카드의 합이 16을 초과하지 않으면 hit 한다`() {
        val dealer = Dealer(
            Cards(
                setOf(
                    Card(Rank.JACK, Suit.DIAMOND),
                    Card(Rank.SIX, Suit.CLOVER),
                ),
            ),
        )
        assertThat(dealer.isPossibleDrawCard()).isTrue
    }

    @Test
    fun `카드의 합이 16을 초과하면 stay 한다`() {
        val dealer = Dealer(
            Cards(
                setOf(
                    Card(Rank.JACK, Suit.DIAMOND),
                    Card(Rank.SEVEN, Suit.CLOVER),
                ),
            ),
        )
        assertThat(dealer.isPossibleDrawCard()).isFalse
    }

    @Test
    fun `유저 2명 중 이긴 유저가 2명이면 딜러는 0승 2패이다`() {
        val dealer = Dealer(Card(Rank.SEVEN, Suit.DIAMOND), Card(Rank.TEN, Suit.SPADE))
        val players = Players(
            Player("jason", Card(Rank.TEN, Suit.DIAMOND), Card(Rank.KING, Suit.DIAMOND)),
            Player("pobi", Card(Rank.ACE, Suit.HEART), Card(Rank.JACK, Suit.CLOVER))
        )
        val participants = Participants(listOf(dealer) + players)
        val dealerResult = GameResult(participants)
        assertThat(dealerResult.winCount).isEqualTo(0)
        assertThat(dealerResult.loseCount).isEqualTo(2)
    }

    @Test
    fun `유저 2명 중 이긴 유저가 1명, 진 유저가 1명이면 딜러는 1승 1패이다`() {
        val dealer = Dealer(Card(Rank.TEN, Suit.CLOVER), Card(Rank.TEN, Suit.SPADE))
        val players = Players(
            Player("jason", Card(Rank.TEN, Suit.DIAMOND), Card(Rank.ACE, Suit.CLOVER)),
            Player("pobi", Card(Rank.DEUCE, Suit.HEART), Card(Rank.JACK, Suit.CLOVER))
        )
        val participants = Participants(listOf(dealer) + players)
        val dealerResult = GameResult(participants)
        assertThat(dealerResult.winCount).isEqualTo(1)
        assertThat(dealerResult.loseCount).isEqualTo(1)
    }

    companion object {
        private fun Dealer(vararg card: Card): Dealer = Dealer(Cards(card.toSet()))
        private fun Player(name: String, vararg card: Card): Player = Player(Cards(card.toSet()), Name(name))
        private fun Players(vararg player: Player): Players = Players(player.toList())
    }
}
