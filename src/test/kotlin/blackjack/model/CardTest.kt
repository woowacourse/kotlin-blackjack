package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드의 총 합을 구하는 기능`() {
        val jack = Card(CardNumber.JACK, Suit.HEART)
        val two = Card(CardNumber.TWO, Suit.HEART)
        val cardList = listOf(jack, two)
        val actual = sum(cardList)
        val expected = 12
        assertThat(actual).isEqualTo(expected)
    }

    fun sum(cardList: List<Card>): Int {
        return cardList.map { it.cardNumber.score }.sum()
    }
}
