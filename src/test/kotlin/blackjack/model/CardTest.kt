package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CardTest {
    @Test
    fun `카드는 모양과 끗수를 가진다`() {
        val card = Card(CardShape.HEART, Denomination.TWO)

        assertAll(
            { assertThat(card.shape).isEqualTo(CardShape.HEART) },
            { assertThat(card.denomination).isEqualTo(Denomination.TWO) },
        )
    }
}
