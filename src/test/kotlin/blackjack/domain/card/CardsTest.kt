package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `새로운 카드를 받아 가진 카드에 추가한다`() {
        val cards = Cards(listOf(Card(CardNumber.KING, CardShape.HEART)))
        assertThat(cards.values.size).isEqualTo(1)
    }

    @Test
    fun `갖고 있는 카드에 Ace가 포함된 경우 Ace를 11로 계산해 숫자의 합을 반환한다`() {
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
    fun `갖고 있는 카드에 Ace가 포함된 경우 Ace를 1로 계산해 숫자의 합을 반환한다`() {
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
