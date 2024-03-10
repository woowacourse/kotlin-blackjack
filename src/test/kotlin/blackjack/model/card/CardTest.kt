package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @CsvSource("0, 다이아몬드", "B, 스페이드", "11, 하트")
    @ParameterizedTest
    fun `유효하지 않은 카드의 번호인 경우 예외가 발생한다`(
        denomination: String,
        suite: String,
    ) {
        val exception = assertThrows<IllegalArgumentException> { Card.of(denomination, suite) }
        assertThat(exception.message).isEqualTo("유효하지 않은 카드의 숫자 혹은 알파벳입니다.")
    }

    @CsvSource("A, diamond", "2, 다Oㅣ아몬드", "10, 하트1")
    @ParameterizedTest
    fun `유효하지 않은 카드의 모양인 경우 예외가 발생한다`(
        denomination: String,
        suite: String,
    ) {
        val exception = assertThrows<IllegalArgumentException> { Card.of(denomination, suite) }
        assertThat(exception.message).isEqualTo("유효하지 않은 카드의 모양입니다.")
    }

    @CsvSource("A, 다이아몬드", "10, 하트", "K, 스페이드")
    @ParameterizedTest
    fun `유효한 카드의 구성요소인 경우 예외가 발생하지 않는다`(
        denomination: String,
        suite: String,
    ) {
        assertDoesNotThrow { Card.of(denomination, suite) }
    }

    @Test
    fun `캐싱된 카드를 가져오는지 확인한다`() {
        val actual = Card.from(TestCardProvider)
        assertThat(actual).isSameAs(Card.of("2", "하트"))
    }
}
