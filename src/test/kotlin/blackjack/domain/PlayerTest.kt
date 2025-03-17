package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Rank.NumberRank
import blackjack.domain.card.Suit
import blackjack.domain.fixture.CARD_KING_SPADE
import blackjack.domain.fixture.CARD_SEVEN_HEART
import blackjack.domain.participant.Player
import blackjack.domain.state.Busted
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    private fun Player(name: String): Player = Player(name, Betting(1_000.0), Deck { it })

    private fun Player.hitCards(vararg cards: Card) {
        cards.forEach { card ->
            hit(card)
        }
    }

    @Test
    fun `플레이어는 이름으로 구분된다`() {
        val player = Player(name = "Eden")
        assertThat(player.name).isEqualTo("Eden")
    }

    @Test
    fun `플레이어는 모든 카드의 합이 21 미만이 될 수 있을 경우 계속해서 카드를 뽑을 수 있다`() {
        val player = Player("Eden")
        player.hitCards(CARD_SEVEN_HEART, CARD_SEVEN_HEART, CARD_SEVEN_HEART)
        assertThrows<IllegalStateException> {
            player.hit(Card(NumberRank.TWO, Suit.SPADE))
        }
    }

    @Test
    fun `플레이어 카드의 합이 21 이하가 될 수 없는 플레이어는 반드시 패배한다`() {
        val player = Player("Eden")
        player.hitCards(
            CARD_SEVEN_HEART,
            CARD_SEVEN_HEART,
            CARD_KING_SPADE,
        )
        assertThat(player.state).isInstanceOf(Busted::class.java)
    }

    @Test
    fun `플레이어의 최종 결과를 알 수 있다`() {
        val player =
            Player("Gio").apply {
                hitCards(CARD_SEVEN_HEART, CARD_SEVEN_HEART, CARD_SEVEN_HEART)
            }
        assertThat(player.state).isInstanceOf(Busted::class.java)
    }
}
