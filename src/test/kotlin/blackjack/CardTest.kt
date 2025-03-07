package blackjack

import blackjack.domain.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `Card는 52개의 인스턴스만 가질 수 있다`() {
        val cards = Card.getAllCard()
        val copied = cards.toList()

        for (i in cards.indices) {
            assertThat(cards[i]).isSameAs(copied[i])
        }
    }
}
