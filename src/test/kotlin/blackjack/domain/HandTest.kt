package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `패에 카드를 추가할 수 있다`() {
        val hand = Hand()
        hand.addCard(
            List(3) { Card.create(CardNumber.ACE, CardPattern.HEART) },
        )

        hand.cards.size shouldBe 3
    }
}
