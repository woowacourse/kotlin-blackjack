package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CardDeckTest {
    @Test
    fun `카드 덱은 첫번째 순서의 카드부터 차례대로 카드를 반환한다`() {
        val cards = listOf((Card(CardShape.HEART, "5")), Card(CardShape.CLOVER, "2"))
        val cardDeck = CardDeck(cards)
        val actual1 = cardDeck.pickCard()
        val actual2 = cardDeck.pickCard()

        assertAll(
            {
                assertThat(actual1).isEqualTo((Card(CardShape.HEART, "5")))
            },
            {
                assertThat(actual2).isEqualTo(Card(CardShape.CLOVER, "2"))
            },
        )
    }
}
