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
    betAmount: Double = 1000.0,
): Player = Player(ParticipantName(name), Hand(numbers.toList()), BetAmount(betAmount)).apply { finishRound() }

class ParticipantsTest {
    @Test
    fun `수익률 계산 테스트`() {
        val dealer = createFinishedDealer(Card(8), Card(9))
        val players =
            listOf(
                createFinishedPlayer("leo", Card(6), Card(10)),
                createFinishedPlayer("yenny", Card(9), Card(11)),
                createFinishedPlayer("hodu", Card(5), Card(10)),
                createFinishedPlayer("eddy", Card(8), Card(9)),
            )
        val gameParticipants = Participants(dealer, players)
        val participantsProfits = gameParticipants.getParticipantsProfits()

        assertThat(participantsProfits).isEqualTo(
            mapOf(
                dealer to 1000.0,
                players [0] to -1000.0,
                players[1] to 1000.0,
                players[2] to -1000.0,
                players[3] to 0.0,
            ),
        )
    }
}
