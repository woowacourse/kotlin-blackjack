package blackjack.domain

import blackjack.model.BlackjackCalculator
import blackjack.model.CardDeck
import blackjack.model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `처음 생성된 참가자는 카드를 2장씩 뽑는다`() {
        // given
        val blackjackCalculator = BlackjackCalculator()
        val player = Player("시아", blackjackCalculator)
        val cardDeck = CardDeck()
        val expectedSize = 2

        // when
        player.draw(cardDeck)

        // then
        assertThat(player.cards.size).isEqualTo(expectedSize)
    }
}
