package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `Cards는 카드를 가진다`() {
        val cards = Cards(listOf(Card(CardNumber.KING, CardShape.HEART)))
        assertThat(cards.values.size).isEqualTo(1)
    }

    @Test
    fun `갖고 있는 카드에 Ace가 포함되고 숫자의 합이 21이 넘지 않으면, 숫자의 합을 구하면, Ace는 11로 계산된다`() {
        val cards = Cards(
            listOf(
                Card(CardNumber.ACE, CardShape.DIAMOND),
                Card(CardNumber.SEVEN, CardShape.CLOVER)
            )
        )
        val actual = cards.sumCardsNumber()
        assertThat(actual).isEqualTo(18)
    }

    @Test
    fun `갖고 있는 카드에 Ace가 포함되고 숫자의 합이 21이 넘으면, 숫자의 합을 구하면, Ace는 1로 계산된다`() {
        val cards = Cards(
            listOf(
                Card(CardNumber.ACE, CardShape.DIAMOND),
                Card(CardNumber.JACK, CardShape.CLOVER),
                Card(CardNumber.EIGHT, CardShape.CLOVER)
            )
        )
        val actual = cards.sumCardsNumber()
        assertThat(actual).isEqualTo(19)
    }
}
