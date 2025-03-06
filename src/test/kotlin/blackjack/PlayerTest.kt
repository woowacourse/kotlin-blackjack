package blackjack

import blackjack.domain.participant.Player
import blackjack.fixture.trumpCardFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 게임을 시작하면 카드를 2장 지급받는다`() {
        val player = Player("페토")
        val fixture = trumpCardFixture()
        fixture.forEach {
            player.addCard(it)
        }
        assertThat(player.cards).containsExactly(*fixture.toTypedArray())
    }
}
