package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Deck
import blackjack.domain.state.GameState
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드를 받을 수 있다`() {
        // Given
        val deck = Deck(mutableListOf(Card(CardNumber.ACE, CardPattern.HEART)))
        val dealer = Dealer(Deck())
        val card = deck.draw()

        // When
        dealer.addCard(card)

        // Then
        dealer.hand shouldContain card
    }

    @Test
    fun `덱에서 카드를 한 장 나눠줄 수 있다`() {
        // Given
        val deck = Deck(mutableListOf(Card(CardNumber.ACE, CardPattern.HEART)))
        val dealer = Dealer(deck)

        // When
        dealer.draw()

        // Then
        deck.cards.size shouldBe 0
    }

    @Test
    fun `게임 상태를 가지고 있다`() {
        val dealer = Dealer(Deck())
        dealer.gameState shouldBe GameState.FIRST_TURN
    }

    @Test
    fun `가지고 있는 패의 총 합을 계산한다`() {
        // Given
        val dealer = Dealer(Deck())
        val cards = List(2) { Card(CardNumber.KING, CardPattern.HEART) }

        // When
        cards.forEach { card ->
            dealer.addCard(card)
        }

        // Then
        dealer.score() shouldBe 20
    }

    @Test
    fun `초기 상태일 경우 카드를 2장 받는다`() {
        val dealer = Dealer(Deck())
        dealer.getDrawAmount() shouldBe 2
    }

    @Test
    fun `패의 총 합이 16 이하인 경우 카드를 1장 받는다`() {
        // Given
        val dealer = Dealer(Deck())
        val cards =
            listOf(
                Card(CardNumber.KING, CardPattern.HEART),
                Card(CardNumber.SIX, CardPattern.CLOVER),
            )

        // When
        cards.forEach { card ->
            dealer.addCard(card)
        }

        // Then
        assertSoftly(dealer) {
            dealer.score() shouldBeLessThanOrEqual 16
            dealer.shouldHit() shouldBe true
            dealer.getDrawAmount() shouldBe 1
        }
    }

    @Test
    fun `버스트가 된 경우 카드를 받을 수 없다`() {
        // Given
        val player = Player("pobi") { true }
        val cards = List(3) { Card(CardNumber.KING, CardPattern.HEART) }

        // When
        cards.forEach { card ->
            player.addCard(card)
        }

        // Then
        shouldThrowExactly<IllegalArgumentException> { player.getDrawAmount() }
    }
}
