package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Deck
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드를 받을 수 있다`() {
        // Given
        val deck = Deck.createCustomDeck(mutableListOf(Card(CardNumber.ACE, CardPattern.HEART)))
        val dealer = Dealer(deck)
        val card = deck.draw()

        // When
        dealer.addCard(card)

        // Then
        dealer.hand shouldContain card
    }

    @Test
    fun `덱에서 카드를 한 장 나눠줄 수 있다`() {
        // Given
        val deck = Deck.createCustomDeck(mutableListOf(Card(CardNumber.ACE, CardPattern.HEART)))
        val dealer = Dealer(deck)

        // When
        dealer.handOut(dealer)

        // Then
        deck.cards.size shouldBe 0
    }

    @Test
    fun `가지고 있는 패의 총 합을 계산한다`() {
        // Given
        val cards = List(2) { Card(CardNumber.KING, CardPattern.HEART) }
        val dealer = Dealer(Deck.createDefaultDeck(), cards)

        // Then
        dealer.score() shouldBe 20
    }

    @Test
    fun `초기 상태일 경우 카드를 2장 받는다`() {
        val dealer = Dealer(Deck.createDefaultDeck())
        dealer.getDrawAmount() shouldBe 2
    }

    @Test
    fun `패의 총 합이 16 이하인 경우 카드를 1장 받는다`() {
        // Given
        val cards =
            listOf(
                Card(CardNumber.KING, CardPattern.HEART),
                Card(CardNumber.SIX, CardPattern.CLOVER),
            )
        val dealer = Dealer(Deck.createDefaultDeck(), cards)

        // Then
        assertSoftly(dealer) {
            score() shouldBeLessThanOrEqual 16
            canHit() shouldBe true
            getDrawAmount() shouldBe 1
        }
    }

    @Test
    fun `버스트가 된 경우 카드를 받을 수 없다`() {
        // Given
        val cards = List(3) { Card(CardNumber.KING, CardPattern.HEART) }
        val dealer = Dealer(Deck.createDefaultDeck(), cards)

        // Then
        assertSoftly(dealer) {
            isBust() shouldBe true
            canHit() shouldBe false
        }
    }
}
