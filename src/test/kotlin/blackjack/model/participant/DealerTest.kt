package blackjack.model.participant

import blackjack.fixture.createBustState
import blackjack.fixture.createCard
import blackjack.fixture.createDealer
import blackjack.fixture.createPlayer
import blackjack.fixture.createRunningState
import blackjack.model.Betting
import blackjack.model.Profit
import blackjack.model.card.Card
import blackjack.model.card.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `dealer 가 플레이어들을 모두 이기면, player 들의 betting 금액 합만큼 아익을 얻는다`() {
        // given
        val dealer = createDealer(state = createBustState())
        val players =
            listOf(
                createPlayer(betting = Betting(10_000), state = createBustState()),
                createPlayer(betting = Betting(10_000), state = createBustState()),
                createPlayer(betting = Betting(10_000), state = createBustState()),
            )
        val expected = Profit(30_000)
        // when
        val actual = dealer.judge(players)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `scoreSum 이 17 미만이면, 17이 될 때까지 hit 를 한다 `() {
        // given
        val dealer =
            createDealer(
                state =
                    createRunningState(
                        createCard(rank = Rank.TEN),
                        createCard(rank = Rank.FIVE),
                    ),
            )
        val onDraw: () -> Card = { createCard(rank = Rank.ACE) }
        val expectedScore = 17
        // when
        dealer.play(onDraw = onDraw, onDone = {})
        val actualScore = dealer.sumScore()
        // then
        assertThat(actualScore).isEqualTo(expectedScore)
    }

    @Test
    fun `scoreSum 이 17 보다 크면, stay 한다`() {
        // given
        val dealer =
            createDealer(
                state =
                    createRunningState(
                        createCard(rank = Rank.TEN),
                        createCard(rank = Rank.SEVEN),
                    ),
            )
        val onDraw: () -> Card = { createCard(rank = Rank.ACE) }
        val expectedScore = 17
        // when
        dealer.play(onDraw, onDone = {})
        val actualScore = dealer.sumScore()
        // then
        assertThat(actualScore).isEqualTo(expectedScore)
    }
}
