package blackjack

import blackjack.model.CardMachineManager
import blackjack.model.Deck
import blackjack.model.HandCards
import blackjack.model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        CardMachineManager.machine = TestCardMachine()
        player = Player("채채", Deck())
    }

    @Test
    fun `플레이어는 카드를 추가로 받을 수 있다`() {
        assertThat(player.getAllCards().split(", ").size).isEqualTo(START_CARD_SIZE)
        player.addCard(true)
        assertThat(player.getAllCards().split(", ").size).isEqualTo(START_CARD_SIZE + 1)
    }

    companion object {
        private const val START_CARD_SIZE = 2
    }
}
