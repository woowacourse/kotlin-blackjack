package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @ParameterizedTest
    @CsvSource(
        "1, 0",
        "13, 3",
    )
    fun `유효한 카드 값을 받아 카드를 생성한다`(
        rawCardNumber: Int,
        rawSuit: Int,
    ) {
        // given
        val cardNumber = CardNumber(rawCardNumber)
        val suit = Suit(rawSuit)

        // when then
        assertDoesNotThrow { Card(cardNumber, suit) }
    }

    @ParameterizedTest
    @CsvSource("0,0,1", "13,1,1", "51,3,13")
    fun `0부터 51까지의 카드 인덱스로 카드를 만들 수 있다`(
        index: Int,
        rawSuit: Int,
        rawCardNumber: Int,
    ) {
        // given
        val actualCard = Card(index)
        val cardNumber = CardNumber(rawCardNumber)
        val suit = Suit(rawSuit)

        // when
        val expectedCard = Card(cardNumber, suit)

        // then
        assertThat(actualCard).isEqualTo(expectedCard)
    }

    @ParameterizedTest
    @CsvSource(
        "0, A스페이드",
        "13, A하트",
        "32, 7다이아몬드",
        "51, K클로버",
    )
    fun `카드의 정보를 문자열로 만들 수 있다`(
        rawCardNumber: Int,
        expectedCardText: String,
    ) {
        // given
        val card = Card(rawCardNumber)
        val actualCardText = card.getCardText()

        // when then
        assertThat(actualCardText).isEqualTo(expectedCardText)
    }
}
