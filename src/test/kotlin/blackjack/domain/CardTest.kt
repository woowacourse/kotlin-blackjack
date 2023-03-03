package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @ParameterizedTest
    @CsvSource(value = ["CLOVER, ACE", "HEART, TEN", "SPADE, TWO", "DIA, JACK"])
    fun `카드의 문양과 숫자를 가져올 수 있다`(mark: CardMark, value: CardValue) {
        val card = Card(mark, value)
        assertThat(card.mark).isEqualTo(mark)
        assertThat(card.value).isEqualTo(value)
    }
}
