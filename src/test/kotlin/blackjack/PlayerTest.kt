package blackjack

import blackjack.model.CardMachineManager
import blackjack.model.PlayerCards
import blackjack.model.Deck
import blackjack.model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        CardMachineManager.machine = TestCardMachine()
        val playerCards = PlayerCards(Deck())
        player = Player("채채", playerCards)
    }

    @Test
    fun `플레이어는 카드를 추가로 받을 수 있다`() {
        assertThat(player.getCards().size).isEqualTo(START_CARD_SIZE)
        player.addCard(true)
        assertThat(player.getCards().size).isEqualTo(START_CARD_SIZE + 1)
    }

    companion object {
        private const val START_CARD_SIZE = 2
    }
}
