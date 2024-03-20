package blackjack.model

import blackjack.state.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    class MockParticipant(name: String) : Participant(name)

    @Test
    fun `정상적인 Hit 상태 체크 테스트 `() {
        val name = "딜러"
        val participant = MockParticipant(name = name)
        assertThat(participant.checkHitState()).isTrue()
    }

    @Test
    fun `참가자들은 게임 시작시 2장의 카드를 갖고 시작 해야한다`() {
        val cardDeck = CardDeck()
        val participant = MockParticipant(name = "꼬상")

        participant.initDraw(cardDeck)
        assertThat(participant.getCards().size).isEqualTo(Participant.INIT_HAND_CARD_COUNT)
    }

    @Test
    fun `참가자는 다른 참가자와 정상적으로 승부를 판단한다`() {
        val winPlayer = MockParticipant(name = "꼬상")
        val losePlayer = MockParticipant(name = "누누")

        val aceCard = Card(Denomination.ACE, Suit.SPADE)
        val kingCard = Card(Denomination.KING, Suit.SPADE)
        winPlayer.draw(aceCard)
        losePlayer.draw(kingCard)

        val result = winPlayer.judgeBlackState(losePlayer)
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `게임에서 질 경우, 수익률은 특정 배수만큼 잃는다`() {
        val participant = MockParticipant(name = "꼬상")
        val state = State.Finish.Bust

        val rate = participant.calculateEarningRate(Result.LOSE, state)
        assertThat(rate).isEqualTo(-1.0)
    }

    @Test
    fun `블랙잭으로 승리할 경우, 수익률은 특정 배수만큼 얻는다`() {
        val participant = MockParticipant(name = "꼬상")
        val state = State.Finish.BlackJack

        val rate = participant.calculateEarningRate(Result.WIN, state)
        assertThat(rate).isEqualTo(1.5)
    }

    @Test
    fun `블랙잭은 아니지만 점수로 이긴 경우, 수익률은 특정 배수만큼 얻는다`() {
        val participant = MockParticipant(name = "꼬상")
        val state = State.Finish.Stay

        val rate = participant.calculateEarningRate(Result.WIN, state)
        assertThat(rate).isEqualTo(1.0)
    }

    @Test
    fun `게임 결과가 무승부인 경우, 수익률은 없어야 한다`() {
        val participant = MockParticipant(name = "꼬상")
        val state = State.Finish.Stay

        val rate = participant.calculateEarningRate(Result.DRAW, state)
        assertThat(rate).isEqualTo(0.0)
    }

    @Test
    fun `블랙잭으로 이긴 경우, 베팅금액의 특정 베수만큼 금액을 얻는다`() {
        val player = Player("꼬상")

        player.setMoney(BettingMoney(1000.0))
        val profit = player.calculateProfit(player, 1.5)

        assertThat(profit).isEqualTo(1500.0)
    }

    @Test
    fun `블랙잭은 아니지만 점수로 이긴 경우, 베팅금액의 특정 베수만큼 금액을 얻는다`() {
        val player = Player("꼬상")

        player.setMoney(BettingMoney(1000.0))
        val profit = player.calculateProfit(player, 1.0)

        assertThat(profit).isEqualTo(1000.0)
    }

    @Test
    fun `게임 결과가 패배인 경우, 베팅금액의 특정 베수만큼 금액을 잃는다`() {
        val player = Player("꼬상")

        player.setMoney(BettingMoney(1000.0))
        val profit = player.calculateProfit(player, -1.0)

        assertThat(profit).isEqualTo(-1000.0)
    }
}
