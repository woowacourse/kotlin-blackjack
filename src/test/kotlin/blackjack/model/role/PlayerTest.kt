package blackjack.model.role

import blackjack.model.card.Card
import blackjack.model.card.CardHand
import blackjack.model.card.CardNumber
import blackjack.model.card.CardShape
import blackjack.model.card.state.Done
import blackjack.model.card.state.Stay
import blackjack.model.result.WinningResultStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class PlayerTest {
    @RepeatedTest(1000)
    fun `플레이어의 턴을 진행하여 완료(블랙잭 혹은, 버스트 혹은, 스테이) 상태가 된다`() {
        val player = Player(PlayerName("심지"))
        val endState = player.runPhase(canDraw = { true }) {}
        assertThat(endState is Done).isTrue
    }

    @Test
    fun `플래이어와 딜러의 점수를 비교하여 승리 여부를 판단한다`() {
        val player = Player(PlayerName("심지"))
        val dealer = Dealer()
        player.state =
            Stay(
                CardHand(
                    Card(CardShape.HEART, CardNumber.SIX),
                    Card(CardShape.HEART, CardNumber.KING),
                ),
            )
        dealer.state =
            Stay(
                CardHand(
                    Card(CardShape.DIAMOND, CardNumber.FIVE),
                    Card(CardShape.CLOVER, CardNumber.TEN),
                ),
            )
        val actualWinningStatus = player.judgeWinning(dealer)

        assertThat(actualWinningStatus).isEqualTo(WinningResultStatus.VICTORY)
    }
}
