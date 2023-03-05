package domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Shape
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `21점을 넘으면 추가적인 카드를 뽑지 못한다`() {
        val cards = Cards(
            listOf(
                Card(CardNumber.K, Shape.HEART),
                Card(CardNumber.K, Shape.SPADE)
            )
        )

        val player = Player(
            PlayerName("우기"),
            cards
        )

        assertThat(
            player.drawCard()
        ).isFalse
    }
}
