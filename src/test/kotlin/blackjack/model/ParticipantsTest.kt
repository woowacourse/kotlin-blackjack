package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun createDealer(vararg numbers: Int): Dealer {
    val hand = Hand(numbers.map { Card(it) }.toList())
    return Dealer(state = Stay(hand))
}

private fun createPlayer(
    name: String,
    vararg numbers: Int,
): Player {
    val hand = Hand(numbers.map { Card(it) })
    return Player(ParticipantName(name), Stay(hand))
}

private fun Card(value: Int): Card {
    return Card(CardNumber.entries.find { it.value == value }!!, CardShape.HEART)
}

class ParticipantsTest {
    @Test
    fun `게임 결과 생성 테스트`() {
        val dealer = createDealer(8, 9)
        val players = listOf(createPlayer("leo", 10, 6), createPlayer("yenny", 9, 11))
        val gameParticipants = Participants(dealer, players)

        val dealerResult = gameParticipants.getDealerWinningState()
        assertThat(dealerResult).isEqualTo(mapOf(WinningResult.WIN to 1, WinningResult.LOSE to 1))

        val playerResult = gameParticipants.getPlayerWinningState()
        assertThat(playerResult[players[0]]).isEqualTo(WinningResult.LOSE)
        assertThat(playerResult[players[1]]).isEqualTo(WinningResult.WIN)
    }
}
