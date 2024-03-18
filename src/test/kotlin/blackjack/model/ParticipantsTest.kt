package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun createDealer(vararg numbers: Card): Dealer {
    val state = ParticipantState.calculateInitialParticipantState(Hand(numbers.toMutableList()), DealerInfo())
    return Dealer(state)
}

private fun Card(value: Int): Card {
    return Card(CardNumber.entries.find { it.value == value }!!, CardShape.HEART)
}

private fun createPlayerWithBetAmount(
    vararg numbers: Card,
    betAmount: Int,
): Player {
    val state =
        ParticipantState.calculateInitialParticipantState(
            Hand(numbers.toMutableList()),
            PlayerInfo(ParticipantName("leo"), ParticipantBetAmount(betAmount)),
        )
    return Player(state)
}

class ParticipantsTest {
    @Test
    fun `딜러는 플레이어가 베당금을 잃으면 그만큼 받고, 플레이어가 배당금을 얻으면 그만큼 잃는다`() {
        val player = createPlayerWithBetAmount(Card(10), Card(7), betAmount = 1000)
        val player2 = createPlayerWithBetAmount(Card(10), Card(9), betAmount = 2000)
        val dealer = createDealer(Card(10), Card(11))
        val participants = Participants(dealer, listOf(player, player2))
        assertThat(participants.calculateFinalProfits()).isEqualTo(
            mapOf(
                dealer to 3000.0,
                player to -1000.0,
                player2 to -2000.0,
            ),
        )
    }

    @Test
    fun `딜러는 플레이어가 베당금을 잃으면 그만큼 받고, 플레이어가 배당금을 얻으며 그민큼 잃는다2`() {
        val player = createPlayerWithBetAmount(Card(10), Card(11), betAmount = 1000)
        val player2 = createPlayerWithBetAmount(Card(10), Card(6), betAmount = 2000)
        val dealer = createDealer(Card(7), Card(11))
        val participants = Participants(dealer, listOf(player, player2))
        assertThat(participants.calculateFinalProfits()).isEqualTo(
            mapOf(
                dealer to 500.0,
                player to 1500.0,
                player2 to -2000.0,
            ),
        )
    }

    @Test
    fun `딜러와 플레이어간의 승패 결과를 ScoreRecord 형식으로 계산한다`() {
        val player = createPlayerWithBetAmount(Card(10), Card(11), betAmount = 1000)
        val player2 = createPlayerWithBetAmount(Card(10), Card(6), betAmount = 2000)
        val dealer = createDealer(Card(7), Card(11))
        val participants = Participants(dealer, listOf(player, player2))
        assertThat(participants.calculateResult()).isEqualTo(
            mapOf(
                dealer to ScoreRecord(1, 1),
                player to ScoreRecord(1, 0),
                player2 to ScoreRecord(0, 1),
            ),
        )
    }

    @Test
    fun `딜러와 플레이어간의 승패 결과를 ScoreRecord 형식으로 계산한다2`() {
        val player = createPlayerWithBetAmount(Card(10), Card(11), betAmount = 1000)
        val player2 = createPlayerWithBetAmount(Card(10), Card(6), betAmount = 2000)
        val dealer = createDealer(Card(10), Card(11))
        val participants = Participants(dealer, listOf(player, player2))
        assertThat(participants.calculateResult()).isEqualTo(
            mapOf(
                dealer to ScoreRecord(1, 0),
                player to ScoreRecord(0, 0),
                player2 to ScoreRecord(0, 1),
            ),
        )
    }
}
