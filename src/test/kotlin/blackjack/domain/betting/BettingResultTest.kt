package blackjack.domain.betting

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.result.BlackjackResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BettingResultTest {
    @Test
    fun `플레이어가 블랙잭으로 이긴 경우 배팅 금액의 1쩜5배를 얻는다`() {
        val player = Player("hatti", BettingMoney(1000)).apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.ACE, CardShape.HEART))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.SIX, CardShape.DIAMOND))
            receive(Card(CardNumber.SEVEN, CardShape.DIAMOND))
            receive(Card(CardNumber.EIGHT, CardShape.DIAMOND))
        }
        val result = BlackjackResult.of(dealer, listOf(player))
        val bettingResult = BettingResult.of(listOf(player), result)

        assertThat(bettingResult.getPlayerEarningMoney(player)).isEqualTo(1500)
    }

    @Test
    fun `플레이어가 딜러가 둘 다 블랙잭인 경우 배팅 금액을 돌려받는다`() {
        val player = Player("hatti", BettingMoney(1000)).apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.ACE, CardShape.HEART))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.ACE, CardShape.HEART))
        }
        val result = BlackjackResult.of(dealer, listOf(player))
        val bettingResult = BettingResult.of(listOf(player), result)

        assertThat(bettingResult.getPlayerEarningMoney(player)).isEqualTo(1000)
    }

    @Test
    fun `플레이어가 패배하는 경우 배팅액을 잃는다`() {
        val player = Player("hatti", BettingMoney(1000)).apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.ACE, CardShape.HEART))
            receive(Card(CardNumber.TWO, CardShape.HEART))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.ACE, CardShape.HEART))
        }
        val result = BlackjackResult.of(dealer, listOf(player))
        val bettingResult = BettingResult.of(listOf(player), result)

        assertThat(bettingResult.getPlayerEarningMoney(player)).isEqualTo(-1000)
    }

    @Test
    fun `딜러의 수익금은 플레이어들의 수익금의 총합에 -1을 곱한 값이다`() {
        val player1 = Player("hatti", BettingMoney(1000)).apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.ACE, CardShape.HEART))
            receive(Card(CardNumber.TWO, CardShape.HEART))
        }
        val player2 = Player("krrong", BettingMoney(1000)).apply {
            receive(Card(CardNumber.KING, CardShape.HEART))
            receive(Card(CardNumber.ACE, CardShape.HEART))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.SIX, CardShape.DIAMOND))
            receive(Card(CardNumber.SEVEN, CardShape.DIAMOND))
            receive(Card(CardNumber.EIGHT, CardShape.DIAMOND))
        }
        val result = BlackjackResult.of(dealer, listOf(player1, player2))
        val bettingResult = BettingResult.of(listOf(player1, player2), result)

        assertThat(bettingResult.getDealerEarningMoney()).isEqualTo(-500)
    }
}
