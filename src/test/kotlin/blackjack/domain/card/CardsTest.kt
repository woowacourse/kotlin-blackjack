package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `새로운 카드를 받아 가진 카드에 추가한다`() {
        val cards = Cards()
        cards.addCard(Card(CardNumber.KING, CardShape.HEART))
        assertThat(cards.values.size).isEqualTo(1)
    }

    @Test
    fun `갖고 있는 카드 숫자의 합을 계산해 반환한다`() {
        val cards = Cards()
        cards.addCard(Card(CardNumber.FOUR, CardShape.DIAMOND))
        cards.addCard(Card(CardNumber.JACK, CardShape.CLOVER))
        val actual = cards.sumCardsNumber()
        assertThat(actual).isEqualTo(14)
    }
}
