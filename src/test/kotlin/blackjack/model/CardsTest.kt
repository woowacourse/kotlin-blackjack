package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드들을 생성자로 카드 리스트를 받는다`() {
        val cards = listOf((Card(CardShape.HEART, Denomination.FIVE)), Card(CardShape.CLOVER, Denomination.TWO))
        val actual = Cards(cards)

        Assertions.assertThat(actual.cards).isEqualTo(cards)
    }
}
