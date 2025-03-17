package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `카드를 받는다`() {
        // Given
        val hand = Hand()
        val card = Card(CardNumber.ACE, CardPattern.CLOVER)

        // When
        hand.addCard(card)

        // Then
        hand.cards shouldContain card
    }

    @Test
    fun `패에 카드가 2장 있으며, 점수가 21점이면 블랙잭이다`() {
        // Given
        val hand = Hand(listOf(Card(CardNumber.ACE, CardPattern.CLOVER), Card(CardNumber.KING, CardPattern.SPADE)))

        // Then
        hand.isBlackjack() shouldBe true
    }

    @Test
    fun `점수가 21점을 초과하면 버스트다`() {
        // Given
        val hand = Hand(List(3) { Card(CardNumber.KING, CardPattern.CLOVER) })

        // Then
        hand.isBust() shouldBe true
    }

    @Test
    fun `점수를 계산한다`() {
        // Given
        val hand = Hand(listOf(Card(CardNumber.ACE, CardPattern.SPADE), Card(CardNumber.KING, CardPattern.CLOVER)))

        // Then
        hand.calculateScore() shouldBe 21
    }
}
