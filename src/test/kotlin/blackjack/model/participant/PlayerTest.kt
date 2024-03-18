package blackjack.model.participant

import blackjack.fixture.createBustState
import blackjack.fixture.createPlayer
import blackjack.model.Profit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `player 가 bust 면, player 는 무조건 betting 금액을 잃는다`() {
        // given
        val bustPlayer: Participant = createPlayer(state = createBustState())
        val bustDealer = Dealer(createBustState())
        val expected = -Profit(10_000)
        // when
        val actual = bustPlayer.judge(bustPlayer.betting, bustDealer)
        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
