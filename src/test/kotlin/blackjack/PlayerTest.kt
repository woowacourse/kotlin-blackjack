package blackjack

import blackjack.model.CardMachineManager
import blackjack.model.Deck
import blackjack.model.Player
import blackjack.model.HandCards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        CardMachineManager.machine = TestCardMachine()
        val handCards = HandCards(Deck())
        player = Player("채채", handCards)
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
