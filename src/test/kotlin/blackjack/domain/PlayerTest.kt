package blackjack.domain

import blackjack.Shape
import blackjack.domain.state.FirstTurn
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class PlayerTest {
    @Test
    fun `이름과 상태를 넘겨받아 플레이어를 생성한다`() {
        val name = "krrong"
        val card1 = Card(Shape.HEART, CardNumber.SIX)
        val card2 = Card(Shape.HEART, CardNumber.SEVEN)
        val state = FirstTurn(CardBunch(card1)).draw(card2)
        assertDoesNotThrow { Player(name, state) }
    }

    @Test
    fun `카드를 받아서 cardBunch에 추가한다`() {
        val name = "krrong"
        val card1 = Card(Shape.HEART, CardNumber.SIX)
        val card2 = Card(Shape.HEART, CardNumber.SEVEN)
        val state = FirstTurn(CardBunch(card1)).draw(card2)

        val player = Player(name, state)
        val card3 = Card(Shape.HEART, CardNumber.NINE)
        player.receiveCard(card3)

        assertThat(player.state.hand.cards.size).isEqualTo(3)
    }
}
