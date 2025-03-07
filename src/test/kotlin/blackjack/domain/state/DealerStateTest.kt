package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.person.Dealer
import blackjack.domain.person.Hand
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerStateTest {
    @Test
    fun `카드가 2개 미만이면 FIRST_TURN을 반환한다`() {
        val dealer = Dealer()

        (dealer.cards().size < 2) shouldBe true
        DealerState.from(dealer) shouldBe DealerState.FIRST_TURN
    }

    @Test
    fun `카드 숫자의 합이 16보다 크면 FINISH를 반환한다`() {
        val hand = Hand()

        hand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        hand.addCard(Card.create(CardNumber.SEVEN, CardPattern.HEART))
        val dealer = Dealer(hand)

        (dealer.score() > 16) shouldBe true
        DealerState.from(dealer) shouldBe DealerState.FINISH
    }

    @Test
    fun `카드 숫자의 합이 16 이하면 HIT를 반환한다`() {
        val hand = Hand()

        hand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        hand.addCard(Card.create(CardNumber.SIX, CardPattern.HEART))
        val dealer = Dealer(hand)

        (dealer.score() <= 16) shouldBe true
        DealerState.from(dealer) shouldBe DealerState.HIT
    }
}
