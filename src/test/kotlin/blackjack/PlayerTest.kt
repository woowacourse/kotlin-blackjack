package blackjack

import blackjack.domain.Player
import blackjack.domain.TrumpCard
import blackjack.domain.enums.CardTier
import blackjack.domain.enums.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 게임을 시작하면 카드를 2장 지급받는다`() {
        val player = Player("페토")
        player.drawCard(TrumpCard(CardTier.K, Shape.Dia))
        player.drawCard(TrumpCard(CardTier.Ace, Shape.Heart))
        assertThat(player.cards.size).isEqualTo(2)
    }
}
