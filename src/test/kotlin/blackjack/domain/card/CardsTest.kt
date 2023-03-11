package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `새로운 카드를 받아 가진 카드에 추가한다`() {
        val cards = Cards(
            Pair(CardNumber.TEN, CardShape.DIAMOND),
            Pair(CardNumber.JACK, CardShape.CLOVER)
        )
        assertThat(cards.values.size).isEqualTo(2)
    }

    @Test
    fun `갖고 있는 카드 숫자의 합을 계산해 반환한다`() {
        val cards = Cards(
            Pair(CardNumber.ONE, CardShape.DIAMOND),
            Pair(CardNumber.JACK, CardShape.CLOVER)
        )
        assertThat(cards.sum()).isEqualTo(21)
    }

    @Test
    fun `카드 숫자를 받아 해당 카드 숫자의 보유 여부를 반환한다`() {
        val cards = Cards(
            Pair(CardNumber.ONE, CardShape.DIAMOND),
            Pair(CardNumber.JACK, CardShape.CLOVER)
        )
        assertThat(cards.containsCardNumber(CardNumber.ONE)).isTrue
    }
}
