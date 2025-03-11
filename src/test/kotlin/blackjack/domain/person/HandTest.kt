package blackjack.domain.person

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `패에 카드를 추가할 수 있다`() {
        val hand = Hand()
        val card = Card.create(CardNumber.ACE, CardPattern.HEART)
        repeat(3) { hand.addCard(card) }

        hand.cards.size shouldBe 3
    }
}
