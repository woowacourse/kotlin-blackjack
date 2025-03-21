package blackjack.domain.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @ParameterizedTest
    @CsvSource(
        "A, 0",
        "K, 3",
    )
    fun `유효한 카드 값을 받아 카드를 생성한다`(
        initial: String,
        rawSuit: Int,
    ) {
        // given
        val cardNumber = CardNumber.getByInitial(initial)
        val suit = Suit.getBySuitIndex(rawSuit)

        // when then
        assertDoesNotThrow { Card(cardNumber, suit) }
    }

    @ParameterizedTest
    @CsvSource("0,0,A", "13,1,A", "51,3,K")
    fun `0부터 51까지의 카드 인덱스로 카드를 만들 수 있다`(
        index: Int,
        rawSuit: Int,
        initial: String,
    ) {
        // given
        val actualCard = Card(index)
        val cardNumber = CardNumber.getByInitial(initial)
        val suit = Suit.getBySuitIndex(rawSuit)

        // when
        val expectedCard = Card(cardNumber, suit)

        // then
        assertThat(actualCard).isEqualTo(expectedCard)
    }
}
