package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class PlayerTest {
    @Test
    fun `이름과 카드뭉치를 넘겨받아 플레이어를 생성한다`() {
        val name = "krrong"
        val card1 = Card.get(Shape.HEART, CardNumber.SIX)
        val card2 = Card.get(Shape.HEART, CardNumber.SEVEN)
        val cardBunch = CardBunch(card1, card2)
        assertDoesNotThrow { Player(name, cardBunch, BettingMoney(100)) }
    }

    @Test
    fun `카드를 받아서 cardBunch에 추가한다`() {
        val name = "krrong"
        val card1 = Card.get(Shape.HEART, CardNumber.SIX)
        val card2 = Card.get(Shape.HEART, CardNumber.SEVEN)
        val cardBunch = CardBunch(card1, card2)

        val player = Player(name, cardBunch, BettingMoney(100))
        val card3 = Card.get(Shape.HEART, CardNumber.NINE)
        player.receiveCard(card3)

        assertThat(player.cardBunch.cards.size).isEqualTo(3)
    }

    @Test
    fun `2장의 카드의 합이 21이면 블랙잭이다`() {
        val name = "krrong"
        val card1 = Card.get(Shape.HEART, CardNumber.ACE)
        val card2 = Card.get(Shape.HEART, CardNumber.JACK)
        val cardBunch = CardBunch(card1, card2)

        val player = Player(name, cardBunch, BettingMoney(100))

        assertThat(player.isBlackjack()).isTrue
    }

    @Test
    fun `3장의 카드의 합이 21이면 블랙잭이 아니다`() {
        val name = "krrong"
        val card1 = Card.get(Shape.HEART, CardNumber.SIX)
        val card2 = Card.get(Shape.HEART, CardNumber.SEVEN)
        val card3 = Card.get(Shape.HEART, CardNumber.EIGHT)
        val cardBunch = CardBunch(card1, card2, card3)

        val player = Player(name, cardBunch, BettingMoney(100))

        assertThat(player.isBlackjack()).isFalse
    }
}
