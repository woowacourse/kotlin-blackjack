package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @ParameterizedTest
    @CsvSource(value = ["CLOVER, ACE", "HEART, TEN", "SPADE, TWO", "DIA, JACK"])
    fun `카드의 문양과 숫자를 가져올 수 있다`(mark: CardMark, value: CardValue) {
        val card = Card(mark, value)
        assertAll(
            { assertThat(card.mark).isEqualTo(mark) },
            { assertThat(card.value).isEqualTo(value) },
        )
    }

    @Test
    fun `카드가 ACE인지 확인할 수 있다`() {
        val card = Card(CardMark.CLOVER, CardValue.ACE)
        assertThat(card.isAce).isTrue
    }

    @Test
    fun `카드가 ACE가 아닌지 확인할 수 있다`() {
        val card = Card(CardMark.CLOVER, CardValue.TWO)
        assertThat(card.isAce).isFalse
    }
}
