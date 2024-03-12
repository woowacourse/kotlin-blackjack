package blackjack.model

import blackjack.model.TestUtils.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun createFinishedDealer(vararg numbers: Card): Dealer {
    return Dealer(hand = Hand(numbers.toList())).apply { finishRound() }
}

private fun createFinishedPlayer(
    name: String,
    vararg numbers: Card,
): Player = Player(ParticipantName(name), Hand(numbers.toList())).apply { finishRound() }

class ParticipantsTest {
    @Test
    fun `게임 결과 생성 테스트`() {
        val dealer = createFinishedDealer(Card(8), Card(9))
        val players =
            listOf(
                createFinishedPlayer("leo", Card(10), Card(6)),
                createFinishedPlayer("yenny", Card(9), Card(11)),
            )
        val gameParticipants = Participants(dealer, players)

        val dealerResult = gameParticipants.getDealerWinningState()
        assertThat(dealerResult).isEqualTo(mapOf(WinningResult.WIN to 1, WinningResult.LOSE to 1))

        val playerResult = gameParticipants.getPlayerWinningState()
        assertThat(playerResult[players[0]]).isEqualTo(WinningResult.LOSE)
        assertThat(playerResult[players[1]]).isEqualTo(WinningResult.WIN)
    }
}
