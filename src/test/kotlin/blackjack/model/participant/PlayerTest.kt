package blackjack.model.participant

import blackjack.fixture.createBustState
import blackjack.fixture.createPlayer
import blackjack.model.Betting
import blackjack.model.Profit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `dealer 가 bust 면, player 는 무조건 betting 금액 만큼 얻는다`() {
        // given
        val bustDealer: Participant = createPlayer(state = createBustState())
        val bustPlayer = createPlayer(betting = Betting(10_000), state = createBustState())
        val expected = Profit(10_000)
        // when
        val actual = bustDealer.judge(bustPlayer.betting, bustPlayer)
        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
