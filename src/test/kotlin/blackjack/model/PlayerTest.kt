package blackjack.model

import blackjack.fixture.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

fun createPlayer(
    name: String = "송둥",
    handCards: HandCards,
) = Player(name, handCards)

fun createPlayer(vararg cards: Card) = Player("송둥", HandCards(*cards))

class PlayerTest {
    @Test
    fun `플레이어가 카드를 받으면 손패가 하나 증가한다`() {
        // given
        val player =
            createPlayer(
                createCard(rank = Rank.Six),
                createCard(rank = Rank.Seven),
            )
        val addedCard = createCard(rank = Rank.Eight)
        val expect =
            HandCards(
                createCard(rank = Rank.Six),
                createCard(rank = Rank.Seven),
                createCard(rank = Rank.Eight),
            )
        // when
        player.receiveHandCards(addedCard)
        val actual = player.handCards
        // then
        assertThat(actual).isEqualTo(expect)
    }
}
