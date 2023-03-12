package domain.result

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.money.Money
import domain.money.Profit
import domain.person.Dealer
import domain.person.Participants
import domain.person.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameProfitTest {

    @Test
    fun `플레이어와 딜러의 수익을 계산한다`() {
        val dealer = Dealer()
        val player1 = Player("베르")
        val player2 = Player("빅스")
        val players = listOf(player1, player2)

        val participants = Participants(dealer, players)
        val bettingMoney = mutableListOf(10000, 20000)
        fun getBettingMoney(name: String) = bettingMoney.removeFirst()

        val gameProfit = GameProfit.from(participants, ::getBettingMoney)

        // when
        participants.players.forEach { player ->
            player.receiveCard(
                listOf(
                    Card(CardShape.HEART, CardNumber.KING),
                    Card(CardShape.HEART, CardNumber.NINE),
                ),
            )
            player.stay()
        }

        participants.dealer.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.TEN),
                Card(CardShape.HEART, CardNumber.QUEEN),
            ),
        )

        // then
        assertThat(gameProfit.getPlayersProfit(participants).toList()).contains(
            player1 to Profit(Money(10000), -1.0),
            player2 to Profit(Money(20000), -1.0),
        )

        assertThat(gameProfit.getDealersProfit(participants)).isEqualTo(Profit(Money(30000), 1.0))
    }
}
