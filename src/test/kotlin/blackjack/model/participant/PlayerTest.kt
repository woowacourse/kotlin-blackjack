package blackjack.model.participant

import blackjack.model.BattingMoney
import blackjack.model.deck.Deck
import blackjack.testmachine.NormalCardMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `초기 카드의 개수는 2이다`() {
        val deck = Deck(NormalCardMachine())
        val player = Player.withInitCards("벼리", BattingMoney.Companion.ofAmount(100), deck::draw)
        assertThat(player.getAllCards().size).isEqualTo(START_CARD_SIZE)
    }

    companion object {
        private const val START_CARD_SIZE = 2
    }
}
