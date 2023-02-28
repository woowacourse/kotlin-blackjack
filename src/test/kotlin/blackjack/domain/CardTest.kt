package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드의 문양과 숫자를 가져올 수 있다`() {
        val card = Card(CardMark.CLOVER, CardValue.ACE)
        assertThat(card.mark).isEqualTo(CardMark.CLOVER)
        assertThat(card.value).isEqualTo(CardValue.ACE)
    }

    @Test
    fun `카드의 문양과 숫자를 가져올 수 있다2`() {
        val card = Card(CardMark.HEART, CardValue.TEN)
        assertThat(card.mark).isEqualTo(CardMark.HEART)
        assertThat(card.value).isEqualTo(CardValue.TEN)
    }

    @Test
    fun `카드의 문양과 숫자를 가져올 수 있다3`() {
        val card = Card(CardMark.SPADE, CardValue.TWO)
        assertThat(card.mark).isEqualTo(CardMark.SPADE)
        assertThat(card.value).isEqualTo(CardValue.TWO)
    }

    @Test
    fun `카드의 문양과 숫자를 가져올 수 있다4`() {
        val card = Card(CardMark.DIA, CardValue.JACK)
        assertThat(card.mark).isEqualTo(CardMark.DIA)
        assertThat(card.value).isEqualTo(CardValue.JACK)
    }
}
