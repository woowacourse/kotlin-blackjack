package blackjack.model.card

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

    @Test
    fun `카드가 에이스이면 true를 반환한다`() {
        val card = Card(CardShape.HEART, Denomination.ACE)

        val actual = card.isAce()

        val expected = true

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드가 에이스가 아니면 false를 반환한다`() {
        val card = Card(CardShape.HEART, Denomination.TWO)

        val actual = card.isAce()

        val expected = false

        assertThat(actual).isEqualTo(expected)
    }
}
