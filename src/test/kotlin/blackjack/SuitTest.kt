package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SuitTest {
    @ParameterizedTest
    @CsvSource(
        "0, 스페이드",
        "1, 하트",
        "2, 다이아몬드",
        "3, 클로버",
    )
    fun `카드 문양에 해당하는 문자열을 알려준다`(
        rawSuitIndex: Int,
        expectedSuitName: String,
    ) {
        // given
        val suit = Suit(rawSuitIndex)

        // when
        val actualSuitName = suit.getSuitName()

        // then
        assertThat(actualSuitName).isEqualTo(expectedSuitName)
    }
}
