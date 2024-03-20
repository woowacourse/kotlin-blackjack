package blackjack.model.card

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SortedCardDrawStrategyTest {
    @Test
    fun `정렬된 카드를 뽑는 전략 테스트`() {
        val firstCard = SortedCardDrawStrategy.drawCard()
        Assertions.assertThat(firstCard).isEqualTo(Card(CardShape.HEART, CardNumber.ACE))

        val secondCard = SortedCardDrawStrategy.drawCard()
        Assertions.assertThat(secondCard).isEqualTo(Card(CardShape.HEART, CardNumber.TWO))

        val thirdCard = SortedCardDrawStrategy.drawCard()
        Assertions.assertThat(thirdCard).isEqualTo(Card(CardShape.HEART, CardNumber.THREE))
    }
}
