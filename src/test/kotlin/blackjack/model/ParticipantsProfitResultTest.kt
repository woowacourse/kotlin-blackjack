package blackjack.model

import model.Card
import model.Cards
import model.Dealer
import model.ParticipantsProfitResult
import model.Player
import model.Profit
import model.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsProfitResultTest {
    @Test
    fun `모든 참가자들의 게임 결과를 갖는다`() {
        val dealerResult = Result(Dealer(), Profit(0L))
        val playersResult = listOf(
            Result(Player("jason", 1_000L), Profit(0L)),
            Result(Player("pobi", 1_000L), Profit(0L)),
        )
        val participantsResult = ParticipantsProfitResult(listOf(dealerResult) + playersResult)
        assertThat(participantsResult.dealerResult.profit.value).isEqualTo(0L)
        assertThat(participantsResult.playersResult[0].profit.value).isEqualTo(0L)
        assertThat(participantsResult.playersResult[1].profit.value).isEqualTo(0L)
    }

    companion object {
        private fun Dealer(vararg card: Card): Dealer = Dealer(Cards(card.toSet()))
        private fun Player(name: String, money: Long): Player = Player.of(Cards(setOf()), name, money)
    }
}
