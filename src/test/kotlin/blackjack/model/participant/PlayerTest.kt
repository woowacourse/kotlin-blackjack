package blackjack.model.participant

import blackjack.fixture.createBlackjackState
import blackjack.fixture.createBustState
import blackjack.fixture.createCard
import blackjack.fixture.createPlayer
import blackjack.fixture.createStopState
import blackjack.model.card.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `player 가 bust 면 무조건 진다`() {
        // given
        val bustPlayer = createPlayer(state = createBustState())
        val bustOther = Dealer(state = createBustState())
        val expected = GameResult.LOSE
        // when
        val actual = bustPlayer.judge(bustOther)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `player 가 bust 가 아니고, other 이 bust 면 이긴다`() {
        // given
        val bustPlayer = createPlayer(state = createStopState())
        val bustOther = Dealer(state = createBustState())
        val expected = GameResult.WIN
        // when
        val actual = bustPlayer.judge(bustOther)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `player 가 blackjack 이고, other 도 blackjack 이면 비긴다`() {
        // given
        val blackJackPlayer = createPlayer(state = createBlackjackState())
        val blackJackOther = Dealer(state = createBlackjackState())
        val expected = GameResult.DRAW
        // when
        val actual = blackJackPlayer.judge(blackJackOther)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `player 가 blackjack 이고, other 도 blackjack 이 아니면 이긴다`() {
        // given
        val blackJackPlayer = createPlayer(state = createBlackjackState())
        val stopDealer = Dealer(state = createStopState())
        val expected = GameResult.WIN
        // when
        val actual = blackJackPlayer.judge(stopDealer)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `player 가 blackjack 이 아니고, other 도 blackjack 이면 진다`() {
        // given
        val stopPlayer = createPlayer(state = createStopState())
        val blackJackDealer = Dealer(state = createBlackjackState())
        val expected = GameResult.LOSE
        // when
        val actual = stopPlayer.judge(blackJackDealer)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `player , other 모두 BlackJack, Bust 상태 가 아닌 때는, 점수가 더 높은 사람이 이긴다`() {
        // given
        val stopPlayer =
            createPlayer(
                state =
                    createStopState(
                        createCard(rank = Rank.TEN),
                        createCard(rank = Rank.TEN),
                    ),
            )
        val stopDealer =
            Dealer(
                state =
                    createStopState(
                        createCard(rank = Rank.TEN),
                        createCard(rank = Rank.NINE),
                    ),
            )
        val expected = GameResult.WIN
        // when
        val actual = stopPlayer.judge(stopDealer)
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
