package blackjack.model

import blackjack.fixture.createCard
import blackjack.fixture.createPlayer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 hit 하면 손패가 하나 증가한다`() {
        // given
        val player =
            createPlayer(
                createCard(rank = Rank.SIX),
                createCard(rank = Rank.SEVEN),
            )
        val addedCard = createCard(rank = Rank.EIGHT)
        val expect =
            Hand(
                createCard(rank = Rank.SIX),
                createCard(rank = Rank.SEVEN),
                createCard(rank = Rank.EIGHT),
            )
        // when
        player.hit(addedCard)
        val actual = player.hand
        // then
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어는 이기면 돈을 받는다`() {
        // given
        val player = createPlayer()
        val expect = Amount(10000)
        // when
        player.changeBudget(Amount(10000))
        // then
        assertThat(player.profit).isEqualTo(expect)
    }
}
