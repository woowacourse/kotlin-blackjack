package blackjack.model.state

import blackjack.model.Money
import blackjack.model.card.Card
import blackjack.model.card.CardShape
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BustTest {
    @ParameterizedTest
    @CsvSource(value = ["1000, -1000"])
    fun `버스트일때 금액을 넣으면 배팅금 -1000원을 반환한다`(
        bettingAmount: Money,
        expected: Money,
    ) {
        val hand =
            Hand(
                Card(CardShape.HEART, Denomination.TWO),
                Card(CardShape.SPADE, Denomination.TEN),
                Card(CardShape.DIAMOND, Denomination.TEN),
            )
        val bust = Bust(hand)

        val actual = bust.expectedProfit(bettingAmount)

        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
