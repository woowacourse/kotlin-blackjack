package blackjack.model

import model.Card
import model.Cards
import model.Dealer
import model.Player
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 카드 두 장을 받을 수 있다`() {
        val cards = Cards(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.ACE, Suit.CLOVER))
        val player = Player.of(cards, "jason", 1_000L)
        assertThat(player.cards.cards).isEqualTo(setOf(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.ACE, Suit.CLOVER)))
    }

    @Test
    fun `플레이어의 처음 보여지는 카드는 두 장이다 `() {
        val cards = Cards(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.CLOVER))
        val player = Player.of(cards, "jason", 1_000L)
        val expected = setOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.CLOVER))
        assertThat(player.getFirstOpenCards().cards).isEqualTo(expected)
    }

    @Test
    fun `플레이어는 카드덱에서 카드 한 장을 뽑을 수 있다`() {
        val cards = Cards(Card(Rank.ACE, Suit.SPADE))
        val player = Player.of(cards, "jason", 1_000L)
        val expected = Card(Rank.ACE, Suit.SPADE)
        assertThat(player.cards.firstCard()).isEqualTo(expected)
    }

    @Test
    fun `카드의 합이 21이 넘지 않으면 카드를 뽑을 수 있는 상태이다`() {
        val cards = Cards(Card(Rank.KING, Suit.DIAMOND), Card(Rank.ACE, Suit.SPADE), Card(Rank.JACK, Suit.CLOVER))
        val player = Player.of(cards, "jason", 1_000L)
        assertThat(player.isPossibleDrawCard()).isTrue
    }

    @Test
    fun `카드의 합이 21이 넘으면 bust이다`() {
        val cards = Cards(Card(Rank.KING, Suit.DIAMOND), Card(Rank.JACK, Suit.SPADE), Card(Rank.JACK, Suit.CLOVER))
        val player = Player.of(cards, "jason", 1_000L)
        assertThat(player.isBust()).isTrue
    }

    @Test
    fun `카드의 수가 2장이고, 카드의 합이 21이면 BLACKJACK이다`() {
        val cards = Cards(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.JACK, Suit.SPADE))
        val player = Player.of(cards, "jason", 1_000L)
        assertThat(player.isBlackJack()).isTrue
    }

    @Test
    fun `플레이어의 베팅이 1_000일 때, 플레이어가 BLACKJACK이라면 이익은 1_500이다`() {
        val dealer = Dealer(Cards(Card(Rank.SEVEN, Suit.DIAMOND), Card(Rank.TEN, Suit.CLOVER)))
        val player = Player.of(Cards(Card(Rank.ACE, Suit.CLOVER), Card(Rank.KING, Suit.DIAMOND)), "jason", 1_000L)
        assertThat(player.getProfitMoney(dealer).value).isEqualTo(1_500L)
    }

    @Test
    fun `플레이어의 베팅이 1_000일 때, 딜러보다 카드 합이 높다면 이익은 1_000이다`() {
        val dealer = Dealer(Cards(Card(Rank.SEVEN, Suit.DIAMOND), Card(Rank.TEN, Suit.CLOVER)))
        val player = Player.of(Cards(Card(Rank.ACE, Suit.CLOVER), Card(Rank.NINE, Suit.DIAMOND)), "jason", 1_000L)
        assertThat(player.getProfitMoney(dealer).value).isEqualTo(1_000L)
    }

    @Test
    fun `플레이어의 베팅이 1_000일 때, 딜러와 카드 합이 같다면 이익은 0이다`() {
        val dealer = Dealer(Cards(Card(Rank.SEVEN, Suit.DIAMOND), Card(Rank.TEN, Suit.CLOVER)))
        val player = Player.of(Cards(Card(Rank.SEVEN, Suit.CLOVER), Card(Rank.TEN, Suit.DIAMOND)), "jason", 1_000L)
        assertThat(player.getProfitMoney(dealer).value).isEqualTo(0)
    }

    @Test
    fun `플레이어의 베팅이 1_000일 때, 플레이어가 bust라면 이익은 -1_000이다`() {
        val dealer = Dealer(Cards(Card(Rank.SEVEN, Suit.DIAMOND), Card(Rank.TEN, Suit.CLOVER)))
        val player = Player.of(Cards(Card(Rank.TEN, Suit.CLOVER), Card(Rank.TEN, Suit.DIAMOND), Card(Rank.DEUCE, Suit.SPADE)), "jason", 1_000L)
        assertThat(player.getProfitMoney(dealer).value).isEqualTo(-1_000L)
    }

    @Test
    fun `플레이어의 베팅이 1_000일 때, 딜러가 bust라면 이익은 1_000이다`() {
        val dealer = Dealer(Cards(Card(Rank.TEN, Suit.DIAMOND), Card(Rank.TEN, Suit.CLOVER), Card(Rank.DEUCE, Suit.CLOVER)))
        val player = Player.of(Cards(Card(Rank.TEN, Suit.HEART), Card(Rank.TEN, Suit.SPADE)), "jason", 1_000L)
        assertThat(player.getProfitMoney(dealer).value).isEqualTo(1_000L)
    }

    companion object {
        private fun Cards(vararg card: Card): Cards = Cards(card.toSet())
    }
}
