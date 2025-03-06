package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @ParameterizedTest
    @CsvSource(
        "1, 0",
        "13, 3",
    )
    fun `유효한 카드 값을 받아 카드를 생성한다`(
        cardNumber: Int,
        suit: Int,
    ) {
        assertDoesNotThrow { Card(cardNumber, suit) }
    }

    @ParameterizedTest
    @CsvSource(
        "14, 0",
        "1, 4",
    )
    fun `유효하지 않은 카드 값을 받으면 카드가 생성되지 않는다`(
        cardNumber: Int,
        suit: Int,
    ) {
        assertThrows<IllegalArgumentException> { Card(cardNumber, suit) }
    }

    @ParameterizedTest
    @CsvSource("0,0,1", "13,1,1", "51,3,13")
    fun `0부터 51까지의 카드 인덱스로 카드를 만들 수 있다`(
        index: Int,
        suit: Int,
        cardNumber: Int,
    ) {
        val actualCard = Card(index)

        val expectedCard = Card(cardNumber, suit)

        assertThat(actualCard).isEqualTo(expectedCard)
    }
}
