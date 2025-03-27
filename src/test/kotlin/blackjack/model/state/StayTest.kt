package blackjack.model.state

import blackjack.model.Money
import blackjack.model.card.Card
import blackjack.model.card.CardShape
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class StayTest {
    @ParameterizedTest
    @CsvSource(value = ["1000, 1000"])
    fun `스테이일때 금액을 넣으면 1배 배팅금을 반환한다`(
        bettingAmount: Money,
        expected: Money,
    ) {
        val hand = Hand(Card(CardShape.HEART, Denomination.ACE), Card(CardShape.SPADE, Denomination.ACE))
        val stay = Stay(hand)

        val actual = stay.expectedProfit(bettingAmount)

        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
