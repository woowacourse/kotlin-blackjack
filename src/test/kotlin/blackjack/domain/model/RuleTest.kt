package blackjack.domain.model

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RuleTest {
    private val cards = List(13) { i -> Card(CardNumber(i + 1), Suit(0)) }

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
        val cards = rawCardNumbers.split(',').map { cards[it.toInt()] }
        val rule = Rule()

        val actualResult = rule.calculateResultByCards(cards)

        assertThat(actualResult).isEqualTo(expectedResult)
    }
}
