package blackjack.model.domain

import blackjack.model.entitiy.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `카드 덱을 전부다 뽑은 상태에서 카드를 뽑아도 카드를 주는지`() {
        repeat(52) {
            CardDeck.pick()
        }

        assertThat(CardDeck.pick().javaClass).isEqualTo(Card::class.java)
    }
}
