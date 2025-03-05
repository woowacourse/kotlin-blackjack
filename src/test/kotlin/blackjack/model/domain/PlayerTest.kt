package blackjack.model.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `카드 숫자의 합을 계산한다`() {
        val player1 = Player("제리")
        player1.receiveCard(Card(Shape.Heart, CardNumber.Ace))
        player1.receiveCard(Card(Shape.Spade, CardNumber.Six))
        assertThat(player1.sumCardNumber).isEqualTo(7)
    }

    @Test
    fun `받은 카드의 목록을 반환한다`() {
        val player1 = Player("제리")
        player1.receiveCard(Card(Shape.Heart, CardNumber.Ace))
        player1.receiveCard(Card(Shape.Spade, CardNumber.Six))
        assertThat(player1.cardDeck).isEqualTo(listOf(Card(Shape.Heart, CardNumber.Ace), Card(Shape.Spade, CardNumber.Six)))
    }
}
