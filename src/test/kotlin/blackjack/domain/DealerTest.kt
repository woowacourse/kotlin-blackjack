package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Deck
import blackjack.domain.person.Dealer
import blackjack.domain.person.Hand
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer
    private lateinit var deck: Deck

    @BeforeEach
    fun setup() {
        dealer = Dealer()
        deck = Deck()
    }

    @Test
    fun `처음 턴에서 카드를 2장 뽑는다`() {
        dealer.draw(deck)
        dealer.cards().size shouldBe 2
    }

    @Test
    fun `추가로 카드를 뽑을 때 1장 뽑는다`() {
        dealer.draw(deck)
        dealer.draw(deck)
        dealer.cards().size shouldBe 3
    }

    @Test
    fun `카드 숫자의 총 합이 16을 초과한 경우 카드를 뽑을 수 없다`() {
        val hand = Hand()
        hand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        hand.addCard(Card.create(CardNumber.SIX, CardPattern.HEART))
        dealer = Dealer(hand)
        dealer.draw(deck)

        dealer.canDraw() shouldBe false
    }
}
