package blackjack.model.participant

import blackjack.fixture.ACE_CARD
import blackjack.fixture.BETTING_10000
import blackjack.fixture.FIVE_CARD
import blackjack.fixture.PROFIT_20000
import blackjack.fixture.SEVEN_CARD
import blackjack.fixture.TEN_CARD
import blackjack.fixture.createBustDealer
import blackjack.fixture.createBustPlayer
import blackjack.fixture.createRunningDealer
import blackjack.fixture.play
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `dealer 가 플레이어들을 모두 이기면, player 들의 betting 금액 합만큼 아익을 얻는다`() {
        // given
        val dealer = createBustDealer()
        val players: List<Player> =
            listOf(
                createBustPlayer(BETTING_10000),
                createBustPlayer(BETTING_10000),
            )
        val expected = PROFIT_20000
        // when
        val actual = dealer.judge(players)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `scoreSum 이 17 미만이면, 17이 될 때까지 hit 를 한다 `() {
        // given
        val dealer = createRunningDealer(TEN_CARD, FIVE_CARD)
        val expectedScore = 17
        // when
        dealer.play(onDraw = { ACE_CARD })
        val actualScore = dealer.sumScore()
        // then
        assertThat(actualScore).isEqualTo(expectedScore)
    }

    @Test
    fun `scoreSum 이 17 보다 크면, stay 한다`() {
        // given
        val dealer = createRunningDealer(TEN_CARD, SEVEN_CARD)
        val expectedScore = 17
        // when
        dealer.play(onDraw = { ACE_CARD })
        val actualScore = dealer.sumScore()
        // then
        assertThat(actualScore).isEqualTo(expectedScore)
    }
}
