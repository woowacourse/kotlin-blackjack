package blackjack.model.participant

import blackjack.fixture.createBlackjackState
import blackjack.fixture.createBustState
import blackjack.fixture.createCard
import blackjack.fixture.createPlayer
import blackjack.fixture.createStopState
import blackjack.model.card.Rank
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `player 가 bust 면, dealer 가 bust 여도 이긴다`() {
        // given
        val bustDealer = Dealer(state = createBustState())
        val bustPlayer = createPlayer(state = createBustState())
        val expected = GameResult.WIN
        // when
        val actual = bustDealer.judge(bustPlayer)
        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `dealer 가 bust 이고, other 가 bust 가 아니면 진다`() {
        // given
        val bustDealer = Dealer(state = createBustState())
        val bustOther = createPlayer(state = createStopState())
        val expected = GameResult.LOSE
        // when
        val actual = bustDealer.judge(bustOther)
        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `dealer 가 blackjack 이고, other 도 blackjack 이면 비긴다`() {
        // given
        val blackJackDealer = Dealer(state = createBlackjackState())
        val blackJackOther = createPlayer(state = createBlackjackState())
        val expected = GameResult.DRAW
        // when
        val actual = blackJackDealer.judge(blackJackOther)
        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `dealer 가 blackjack 이고, other 도 blackjack 이 아니면 이긴다`() {
        // given
        val blackJackDealer = Dealer(state = createBlackjackState())
        val stopDealer = createPlayer(state = createStopState())
        val expected = GameResult.WIN
        // when
        val actual = blackJackDealer.judge(stopDealer)
        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `dealer 가 blackjack 이 아니고, other 도 blackjack 이면 진다`() {
        // given
        val stopPlayer = Dealer(state = createStopState())
        val blackJackDealer = createPlayer(state = createBlackjackState())
        val expected = GameResult.LOSE
        // when
        val actual = stopPlayer.judge(blackJackDealer)
        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `player , other 모두 BlackJack, Bust 상태 가 아닌 때는, 점수가 더 높은 사람이 이긴다`() {
        // given
        val stopPlayer =
            Dealer(
                state =
                    createStopState(
                        createCard(rank = Rank.TEN),
                        createCard(rank = Rank.TEN),
                    ),
            )
        val stopDealer =
            createPlayer(
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
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
