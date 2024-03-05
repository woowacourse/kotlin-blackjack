package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `덱에는 카드 52장이 들어있다`() {
        val deck = Deck()
        val actual = deck.deck.size
        assertThat(actual).isEqualTo(52)
    }

    @Test
    fun `덱에는 suit가 HEART인 카드가 13장 들어있다`() {
        val deck = Deck()
        val actual = deck.deck.filter { it.suit == Suit.HEART }.size
        assertThat(actual).isEqualTo(13)
    }

    @Test
    fun `덱에는 suit가 SPADE인 카드가 13장 들어있다`() {
        val deck = Deck()
        val actual = deck.deck.filter { it.suit == Suit.SPADE }.size
        assertThat(actual).isEqualTo(13)
    }

    @Test
    fun `덱에는 suit가 DIAMOND인 카드가 13장 들어있다`() {
        val deck = Deck()
        val actual = deck.deck.filter { it.suit == Suit.DIAMOND }.size
        assertThat(actual).isEqualTo(13)
    }

    @Test
    fun `덱에는 suit가 CLOVER인 카드가 13장 들어있다`() {
        val deck = Deck()
        val actual = deck.deck.filter { it.suit == Suit.CLOVER }.size
        assertThat(actual).isEqualTo(13)
    }
}
