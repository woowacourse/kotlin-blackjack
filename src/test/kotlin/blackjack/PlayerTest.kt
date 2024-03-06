package blackjack

import blackjack.model.CardMachineManager
import blackjack.model.Deck
import blackjack.model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp(){
        CardMachineManager.machine = TestCardMachine()
        player = Player("채채", Deck())
    }


    @Test
    fun `플레이어는 카드를 추가로 받을 수 있다`() {
        val cards = player.cards
        assertThat(cards.size).isEqualTo(START_CARD_SIZE)
        player.addCard(true)
        assertThat(player.cards.size).isEqualTo(START_CARD_SIZE + 1)
    }

    @Test
    fun `플레이어는 자신의 카드 결과값을 계산할 수 있다`(){
        val result = player.calculateCardScore()
        assertThat(result).isEqualTo(TEST_INIT_CARD_SCORE)
    }

    companion object {
        private const val START_CARD_SIZE = 2
        private const val TEST_INIT_CARD_SCORE = 5
    }
}
