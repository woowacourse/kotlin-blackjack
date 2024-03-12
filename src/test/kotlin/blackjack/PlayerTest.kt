package blackjack

import blackjack.model.deck.Deck
import blackjack.model.participant.Player
import blackjack.testmachine.BlackjackCardMachine
import blackjack.testmachine.BustCardMachine
import blackjack.testmachine.NormalCardMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("채채", Deck(NormalCardMachine()))
    }

    @Test
    fun `플레이어는 카드의 합이 21 이하일 시 카드를 추가로 받을 수 있다`() {
        assertThat(player.getAllCards().split(", ").size).isEqualTo(START_CARD_SIZE)
        player.addCard()
        assertThat(player.getAllCards().split(", ").size).isEqualTo(START_CARD_SIZE + 1)
    }

    @Test
    fun `플레이어의 카드의 합이 21 초과일 시 버스트된다`() {
        val deck = Deck(BustCardMachine())
        val player = Player("채채", deck)
        player.addCard()
        assertThat(player.isBust()).isTrue()
    }

    @Test
    fun `플레이어는 블랙잭 여부를 반환할 수 있다`() {
        val deck = Deck(BlackjackCardMachine())
        val player = Player("채채", deck)
        assertThat(player.isBlackjack()).isTrue()
    }

    companion object {
        private const val START_CARD_SIZE = 2
    }
}
