package blackjack

import blackjack.model.deck.Deck
import blackjack.model.participant.Player
import blackjack.state.Blackjack
import blackjack.state.Running
import blackjack.state.Start
import blackjack.testmachine.BlackjackCardMachine
import blackjack.testmachine.NormalCardMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StartTest {
    @Test
    fun `게임이 시작되면 카드를 두 장 받는지 확인한다`() {
        // Given
        val player = Player("채채", Deck())

        // When
        player.state = Start(player)

        // Then
        assertThat(player.getAllCards().split(SPLIT_DELIMITER).size).isEqualTo(START_CARD_SIZE)
    }

    @Test
    fun `게임이 시작되고 블랙잭이라면 Blackjack상태로 변한다`() {
        val player = Player("채채", Deck(BlackjackCardMachine()))

        // Then
        assertThat(player.state).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun `게임이 시작되고 블랙잭이 아니라면 Start상태를 유지한다`() {
        // Given
        val player = Player("채채", Deck(NormalCardMachine()))

        // Then
        assertThat(player.state).isInstanceOf(Running::class.java)
    }

    companion object {
        private const val START_CARD_SIZE = 2
        private const val SPLIT_DELIMITER = ", "
    }
}
