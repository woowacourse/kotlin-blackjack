package blackjack.model

import blackjack.fixture.createCard
import blackjack.fixture.createPlayer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 카드를 받으면 손패가 하나 증가한다`() {
        // given
        val player =
            createPlayer(
                createCard(rank = Rank.SIX),
                createCard(rank = Rank.SEVEN),
            )
        val addedCard = createCard(rank = Rank.EIGHT)
        val expect =
            HandCards(
                createCard(rank = Rank.SIX),
                createCard(rank = Rank.SEVEN),
                createCard(rank = Rank.EIGHT),
            )
        // when
        player.receiveHandCards(addedCard)
        val actual = player.handCards
        // then
        assertThat(actual).isEqualTo(expect)
    }
}
