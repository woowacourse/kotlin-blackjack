package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SuitTest {

    @ParameterizedTest
    @CsvSource("SPADE, SPADE", "HEART, HEART", "DIAMOND, DIAMOND", "CLOVER, CLOVER")
    fun `카드 모양에는 스페이드, 하트, 다이아몬드, 클로버가 있다`(suit: Suit, expected: String) {
        assertThat(suit.name).isEqualTo(expected)
    }
}
