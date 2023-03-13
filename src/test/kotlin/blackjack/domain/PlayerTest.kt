package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `21점을 넘으면 추가적인 카드를 뽑지 못한다`() {
        val player = createPlayer(CardNumber.K, CardNumber.K)

        assertThat(
            player.drawCard(CardPack())
        ).isEqualTo(DrawState.IMPOSSIBLE)
    }

    private val card = Card(CardNumber.ONE, Shape.SPADE)

    private fun createPlayer(firstCardNumber: CardNumber, secondCardNumber: CardNumber): Player {
        return Player(PlayerName("name"), CardHand(listOf(Card(firstCardNumber, Shape.SPADE), Card(secondCardNumber, Shape.SPADE))), BetAmount(1000))
    }
}
