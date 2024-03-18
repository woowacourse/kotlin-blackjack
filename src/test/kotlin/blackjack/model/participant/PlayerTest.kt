package blackjack.model.participant

import blackjack.fixture.BETTING_10000
import blackjack.fixture.PROFIT_10000
import blackjack.fixture.createBustDealer
import blackjack.fixture.createBustPlayer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `player 가 bust 면, player 는 무조건 betting 금액을 잃는다`() {
        // given
        val bustPlayer = createBustPlayer(BETTING_10000)
        val bustDealer = createBustDealer()
        val expected = -PROFIT_10000
        // when
        val actual = bustPlayer.judge(bustPlayer.betting, bustDealer)
        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
