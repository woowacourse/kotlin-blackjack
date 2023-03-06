package blackjack.model

import model.Card
import model.Cards
import model.Dealer
import model.Name
import model.Player
import model.Players
import model.Rank
import model.Result
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
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
        assertThat(dealer.isHit()).isTrue
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
        assertThat(dealer.isHit()).isFalse
    }

    @Test
    fun `유저 2명 중 이긴 유저가 2명이면 딜러는 0승 2패이다`() {
        val dealer = Dealer(Card(Rank.SEVEN, Suit.DIAMOND), Card(Rank.TEN, Suit.SPADE))
        val players = Players(
            Player("jason", Card(Rank.TEN, Suit.DIAMOND), Card(Rank.KING, Suit.DIAMOND)),
            Player("pobi", Card(Rank.ACE, Suit.HEART), Card(Rank.JACK, Suit.CLOVER))
        )
        val dealerResult = dealer.getGameResult(players)
        assertThat(dealerResult[Result.WIN]).isEqualTo(0)
        assertThat(dealerResult[Result.LOSE]).isEqualTo(2)
    }

    companion object {
        private fun Dealer(vararg card: Card): Dealer = Dealer(Cards(card.toSet()))
        private fun Player(name: String, vararg card: Card): Player = Player(Cards(card.toSet()), Name(name))
        private fun Players(vararg player: Player): Players = Players(player.toList())
    }
}
