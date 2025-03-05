package blackjack.model.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    val player1 = Player("제리")

    @BeforeEach
    fun setup() {
        player1.receiveCard(Card(Shape.Heart, CardNumber.Ace))
        player1.receiveCard(Card(Shape.Spade, CardNumber.Six))
    }

    @Test
    fun `카드 숫자의 합을 계산한다`() {
        assertThat(player1.sumCardNumber).isEqualTo(7)
    }

    @Test
    fun `받은 카드의 목록을 반환한다`() {
        assertThat(player1.cardDeck).isEqualTo(listOf(Card(Shape.Heart, CardNumber.Ace), Card(Shape.Spade, CardNumber.Six)))
    }

    @Test
    fun `카드 숫자의 합을 토대로 bust를 판단한다`() {
        player1.receiveCard(Card(Shape.Heart, CardNumber.Queen))
        player1.receiveCard(Card(Shape.Spade, CardNumber.Queen))

        player1.judgeBust()
        assertThat(player1.alive).isFalse()
    }
}
