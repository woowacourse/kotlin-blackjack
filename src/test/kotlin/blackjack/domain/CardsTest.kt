package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드 목록에 카드를 추가한다`() {
        val cards = Cards()

        cards.add(Card.of(1))
        cards.add(Card.of(3))
        cards.add(Card.of(5))

        assertThat(cards.items.size).isEqualTo(3)
    }
}
