package blackjack

import blackjack.model.Calculator
import blackjack.model.Card
import blackjack.model.CardNumber
import blackjack.model.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CalculatorTest {
    private lateinit var cards: MutableSet<Card>

    @BeforeEach
    fun setUp() {
        cards = mutableSetOf(Card(CardNumber.FOUR, CardSymbol.CLOVER), Card(CardNumber.SIX, CardSymbol.HEART))
    }

    @Test
    fun `카드들의 점수 합 계산`() {
        val actual = Calculator.calculateScore(cards)

        assertThat(actual).isEqualTo(10)
    }

    @Test
    fun `Ace가 1장 있을때 카드들의 점수 합 계산`() {
        cards.add(Card(CardNumber.ACE, CardSymbol.CLOVER))
        val actual = Calculator.calculateScore(cards)

        assertThat(actual).isEqualTo(21)
    }

    @Test
    fun `Ace가 2장 있을때 카드들의 점수 합 계산`() {
        cards.add(Card(CardNumber.ACE, CardSymbol.CLOVER))
        cards.add(Card(CardNumber.ACE, CardSymbol.SPADE))
        val actual = Calculator.calculateScore(cards)

        assertThat(actual).isEqualTo(12)
    }
}
