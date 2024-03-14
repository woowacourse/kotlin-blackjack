package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardMakerTest {
    private val oneDeck =
        listOf(
            Card(CardNumber.ACE, Suit.HEART),
            Card(CardNumber.ACE, Suit.CLOVER),
            Card(CardNumber.ACE, Suit.SPADE),
            Card(CardNumber.ACE, Suit.DIAMOND),
            Card(CardNumber.TWO, Suit.HEART),
            Card(CardNumber.TWO, Suit.CLOVER),
            Card(CardNumber.TWO, Suit.SPADE),
            Card(CardNumber.TWO, Suit.DIAMOND),
            Card(CardNumber.THREE, Suit.HEART),
            Card(CardNumber.THREE, Suit.CLOVER),
            Card(CardNumber.THREE, Suit.SPADE),
            Card(CardNumber.THREE, Suit.DIAMOND),
            Card(CardNumber.FOUR, Suit.HEART),
            Card(CardNumber.FOUR, Suit.CLOVER),
            Card(CardNumber.FOUR, Suit.SPADE),
            Card(CardNumber.FOUR, Suit.DIAMOND),
            Card(CardNumber.FIVE, Suit.HEART),
            Card(CardNumber.FIVE, Suit.CLOVER),
            Card(CardNumber.FIVE, Suit.SPADE),
            Card(CardNumber.FIVE, Suit.DIAMOND),
            Card(CardNumber.SIX, Suit.HEART),
            Card(CardNumber.SIX, Suit.CLOVER),
            Card(CardNumber.SIX, Suit.SPADE),
            Card(CardNumber.SIX, Suit.DIAMOND),
            Card(CardNumber.SEVEN, Suit.HEART),
            Card(CardNumber.SEVEN, Suit.CLOVER),
            Card(CardNumber.SEVEN, Suit.SPADE),
            Card(CardNumber.SEVEN, Suit.DIAMOND),
            Card(CardNumber.EIGHT, Suit.HEART),
            Card(CardNumber.EIGHT, Suit.CLOVER),
            Card(CardNumber.EIGHT, Suit.SPADE),
            Card(CardNumber.EIGHT, Suit.DIAMOND),
            Card(CardNumber.NINE, Suit.HEART),
            Card(CardNumber.NINE, Suit.CLOVER),
            Card(CardNumber.NINE, Suit.SPADE),
            Card(CardNumber.NINE, Suit.DIAMOND),
            Card(CardNumber.TEN, Suit.HEART),
            Card(CardNumber.TEN, Suit.CLOVER),
            Card(CardNumber.TEN, Suit.SPADE),
            Card(CardNumber.TEN, Suit.DIAMOND),
            Card(CardNumber.JACK, Suit.HEART),
            Card(CardNumber.JACK, Suit.CLOVER),
            Card(CardNumber.JACK, Suit.SPADE),
            Card(CardNumber.JACK, Suit.DIAMOND),
            Card(CardNumber.QUEEN, Suit.HEART),
            Card(CardNumber.QUEEN, Suit.CLOVER),
            Card(CardNumber.QUEEN, Suit.SPADE),
            Card(CardNumber.QUEEN, Suit.DIAMOND),
            Card(CardNumber.KING, Suit.HEART),
            Card(CardNumber.KING, Suit.CLOVER),
            Card(CardNumber.KING, Suit.SPADE),
            Card(CardNumber.KING, Suit.DIAMOND),
        )

    @Test
    fun `조커를 제외한 모든 트럼프 카드 52장을 제공한다`() {
        val cards = CardMaker.cards
        val actual = cards.toSet().intersect(oneDeck.toSet()).size
        assertThat(actual).isEqualTo(52)
    }

    @Test
    fun `조커를 제외한 모든 트럼프 카드 52장을 섞어서 제공한다`() {
        val shuffledCards = CardMaker().shuffledCards
        val actual = shuffledCards.toSet().intersect(oneDeck.toSet()).size
        assertThat(actual).isEqualTo(52)
    }
}
