package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.person.Hand
import blackjack.domain.person.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerStateTest {
    @Test
    fun `카드가 2개 미만이면 FIRST_TURN을 반환한다`() {
        val player = Player("test")

        (player.cards().size < 2) shouldBe true
        PlayerState.from(player) shouldBe PlayerState.FIRST_TURN
    }

    @Test
    fun `카드 숫자의 합이 21보다 크면 BUST를 반환한다`() {
        val hand = Hand()

        hand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        hand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        hand.addCard(Card.create(CardNumber.TWO, CardPattern.HEART))

        val player = Player("test", hand)

        (player.score() > 21) shouldBe true
        PlayerState.from(player) shouldBe PlayerState.BUST
    }

    @Test
    fun `카드 숫자의 합이 21 이하면 HIT를 반환한다`() {
        val hand = Hand()

        hand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        hand.addCard(Card.create(CardNumber.SIX, CardPattern.HEART))
        val player = Player("test", hand)

        (player.score() <= 21) shouldBe true
        PlayerState.from(player) shouldBe PlayerState.HIT
    }
}
