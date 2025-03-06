package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RuleTest {
    private val preparedCards = List(12) { Card(it) }

    @ParameterizedTest
    @CsvSource(
        "12:1,1",
        "19:1,8",
        "13:1,1,1",
        "14:1,1,1,1",
        "21:1,10",
        "12:10,1,1",
        "21:1,1,1,1,7",
        delimiter = ':',
    )
    fun `룰에게 손패의 결과를 물어볼 수 있다`(
        expectedResult: Int,
        rawCardNumbers: String,
    ) {
        val cards = rawCardNumbers.split(',').map { preparedCards[it.toInt() - 1] }

        val actualResult = Rule.calculateResultByCards(cards)

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @ParameterizedTest
    @CsvSource(
        "true:1,1",
        "true:1,8",
        "true:1,1,1",
        "true:10,1,1",
        "true:1,1,1,1,7",
        "false:10,1,1,10",
        "false:10,7,5",
        "false:1,1,1,1,7,2,9",
        delimiter = ':',
    )
    fun `룰에게 플레이어의 드로우 여부를 받아올 수 있다`(
        expectedIsDrawable: Boolean,
        rawCardNumbers: String,
    ) {
        val cards = rawCardNumbers.split(',').map { preparedCards[it.toInt() - 1] }

        val actualIsDrawable = Rule.isBurst(cards)

        assertThat(actualIsDrawable).isEqualTo(expectedIsDrawable)
    }

    @ParameterizedTest
    @CsvSource(
        "true:1,1",
        "true:1,1,1",
        "false:10,1",
        "false:1,1,1,1,7",
        "false:1,8",
        delimiter = ':',
    )
    fun `룰에게 딜러의 드로우 여부를 받아올 수 있다`(
        expectedShouldDraw: Boolean,
        rawCardNumbers: String,
    ) {
        val cards = rawCardNumbers.split(',').map { preparedCards[it.toInt() - 1] }

        val actualShouldDraw = Rule.calculateShouldDrawByCards(cards)

        assertThat(actualShouldDraw).isEqualTo(expectedShouldDraw)
    }
}
