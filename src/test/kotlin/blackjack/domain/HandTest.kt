package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.person.Hand
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

    @Test
    fun `패에 들고 있는 카드의 합을 구할 수 있다`() {
        val hand = Hand()
        hand.addCard(List(3) { Card.create(CardNumber.ACE, CardPattern.HEART) })

        hand.score() shouldBe 13
    }
}
