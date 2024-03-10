package blackjack.model.participant

import blackjack.fixture.createCard
import blackjack.fixture.createPlayer
import blackjack.model.card.Rank
import blackjack.model.card.Hand
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
}
