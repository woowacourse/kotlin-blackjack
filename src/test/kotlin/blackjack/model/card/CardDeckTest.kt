package blackjack.model.card

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `하나의 덱의 카드 개수만큼 카드를 뽑으면, 유효한 카드가 반환된다`() {
        repeat(51) { CardDeck.pick() }
        assertNotNull(CardDeck.pick())
    }

    @Test
    fun `하나의 덱의 카드 개수를 초과한 횟수 만큼 카드를 뽑으면, null을 반환한다`() {
        repeat(52) { CardDeck.pick() }
        assertNull(CardDeck.pick())
    }
}
