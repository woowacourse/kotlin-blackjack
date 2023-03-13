package blackjack.model

import model.Card
import model.CardDeck
import model.Dealer
import model.Player
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 카드 두 장을 받을 수 있다`() {
        val cardDeck = CardDeck(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.ACE, Suit.CLOVER))
        val dealer = Dealer()
        dealer.drawFirst(cardDeck)
        val expected = setOf(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.ACE, Suit.CLOVER))
        assertThat(dealer.cards.cards).isEqualTo(expected)
    }

    @Test
    fun `딜러의 처음 보여지는 카드는 한 장이다`() {
        val cardDeck = CardDeck(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.CLOVER))
        val dealer = Dealer()
        dealer.drawFirst(cardDeck)
        val expected = setOf(Card(Rank.ACE, Suit.SPADE))
        assertThat(dealer.getFirstOpenCards().cards).isEqualTo(expected)
    }

    @Test
    fun `딜러는 카드덱에서 카드 한 장을 뽑을 수 있다`() {
        val cardDeck = CardDeck(Card(Rank.ACE, Suit.SPADE))
        val dealer = Dealer()
        dealer.drawCard(cardDeck)
        val expected = setOf(Card(Rank.ACE, Suit.SPADE))
        assertThat(dealer.cards.cards).isEqualTo(expected)
    }

    @Test
    fun `카드의 합이 16을 초과하지 않으면 hit 한다`() {
        val cardDeck = CardDeck(Card(Rank.JACK, Suit.DIAMOND), Card(Rank.SIX, Suit.CLOVER))
        val dealer = Dealer()
        dealer.drawFirst(cardDeck)
        assertThat(dealer.isPossibleDrawCard()).isTrue
    }

    @Test
    fun `카드의 합이 16을 초과하면 stay 한다`() {
        val cardDeck = CardDeck(Card(Rank.JACK, Suit.DIAMOND), Card(Rank.SEVEN, Suit.CLOVER))
        val dealer = Dealer()
        dealer.drawFirst(cardDeck)
        assertThat(dealer.isPossibleDrawCard()).isFalse
    }

    @Test
    fun `딜러는 참가자가 졌을 경우 참가자의 베팅 금액을 이익으로 얻는다`() {
        val cardDeck = CardDeck(
            Card(Rank.ACE, Suit.SPADE),
            Card(Rank.TEN, Suit.SPADE),
            Card(Rank.ACE, Suit.HEART),
            Card(Rank.ACE, Suit.CLOVER),
        )
        val dealer = Dealer()
        dealer.drawFirst(cardDeck)
        val player = Player.of("jaosn", 1_000L)
        assertThat(dealer.getProfitMoney(player).value).isEqualTo(1_000L)
    }

    companion object {
        private fun CardDeck(vararg card: Card): CardDeck = CardDeck(card.toList())
    }
}
