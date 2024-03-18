package blackjack

import blackjack.model.deck.Deck
import blackjack.model.participant.Player
import blackjack.state.State
import blackjack.testmachine.BlackjackCardMachine
import blackjack.testmachine.BustCardMachine
import blackjack.testmachine.NormalCardMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StateTest {
    @Test
    fun `게임이 시작되면 카드를 두 장 받는지 확인한다`() {
        // Given
        val player = Player("채채", Deck())

        // When
        player.state = State.Start(player)

        // Then
        assertThat(player.getAllCards().split(SPLIT_DELIMITER).size).isEqualTo(START_CARD_SIZE)
    }

    @Test
    fun `게임이 시작되고 블랙잭이라면 Blackjack상태로 변한다`() {
        val player = Player("채채", Deck(BlackjackCardMachine()))

        // Then
        assertThat(player.state).isInstanceOf(State.Finished.Blackjack::class.java)
    }

    @Test
    fun `게임이 시작되고 블랙잭이 아니라면 Running상태로 변한다`() {
        // Given
        val player = Player("채채", Deck(NormalCardMachine()))

        // Then
        assertThat(player.state).isInstanceOf(State.Running::class.java)
    }

    @Test
    fun `히트를 하면 카드를 한 장을 받아 총 3장인지 확인한다`() {
        // Given
        val player = Player("채채", Deck(NormalCardMachine()))
        player.state = State.Start(player)

        // When
        player.state = State.Hit(player).decisionState()

        // Then
        assertThat(player.getAllCards().split(SPLIT_DELIMITER).size).isEqualTo(START_CARD_SIZE + 1)
    }

    @Test
    fun `히트를 하고 버스트가 되면 Bust상태로 변한다`() {
        // Given
        val player = Player("채채", Deck(BustCardMachine()))
        player.state = State.Start(player)

        // When
        player.state = State.Hit(player).decisionState()

        // Then
        assertThat(player.state).isInstanceOf(State.Finished.Bust::class.java)
    }

    @Test
    fun `히트를 하고 버스트가 아니라면 Running상태로 변한다`() {
        // Given
        val player = Player("채채", Deck(NormalCardMachine()))
        player.state = State.Start(player).decisionState()

        // Then
        assertThat(player.state).isInstanceOf(State.Running::class.java)
    }

    @Test
    fun `히트를 하지 않고 스테이를 하면 Stay상태로 변한다`() {
        // Given
        val player = Player("채채", Deck(NormalCardMachine()))
        player.state = State.Start(player)

        // When
        player.state = State.Finished.Stay

        // Then
        assertThat(player.state).isInstanceOf(State.Finished.Stay::class.java)
    }

    companion object {
        private const val START_CARD_SIZE = 2
        private const val SPLIT_DELIMITER = ", "
    }
}
