package blackjack

import blackjack.model.Card
import blackjack.model.CardNumber
import blackjack.model.CardSymbol
import blackjack.model.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ScoreTest {
    private lateinit var cards: MutableSet<Card>

    @BeforeEach
    fun setUp() {
        cards = mutableSetOf(Card(CardNumber.FOUR, CardSymbol.CLOVER), Card(CardNumber.SIX, CardSymbol.HEART))
    }

    @Test
    fun `카드들의 점수 합 계산`() {
        val actual = Score(cards).point

        assertThat(actual).isEqualTo(10)
    }

    @Test
    fun `기존 스코어 10에서 Ace가 1장 드로우 했을때 카드들의 점수 합 계산`() {
        cards.add(Card(CardNumber.ACE, CardSymbol.CLOVER))
        val actual = Score(cards).point

        assertThat(actual).isEqualTo(21)
    }

    @Test
    fun `기존 스코어 10에서 Ace 2장 드로우 했을때 카드들의 점수 합 계산`() {
        cards.add(Card(CardNumber.ACE, CardSymbol.CLOVER))
        cards.add(Card(CardNumber.ACE, CardSymbol.SPADE))
        val actual = Score(cards).point

        assertThat(actual).isEqualTo(12)
    }
}
