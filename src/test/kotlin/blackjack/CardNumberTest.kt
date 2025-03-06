package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardNumberTest {
    @ParameterizedTest
    @CsvSource(
        "1, A",
        "7, 7",
        "11, J",
        "12, Q",
        "13, K",
    )
    fun `카드 번호에 해당하는 문자열을 알려준다`(
        rawCardNumber: Int,
        expectedCardNumberName: String,
    ) {
        // given
        val cardNumber = CardNumber(rawCardNumber)

        // when
        val actualCardNumberName = cardNumber.getCardNumberName()

        // then
        assertThat(actualCardNumberName).isEqualTo(expectedCardNumberName)
    }
}
