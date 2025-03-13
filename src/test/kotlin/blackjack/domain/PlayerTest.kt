package blackjack.domain

import blackjack.domain.Rank.FaceRank
import blackjack.domain.Rank.NumberRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    private fun Player(name: String): Player = Player(name, Betting(0))

    private fun Player.draw(vararg cards: Card) {
        cards.forEach { card ->
            draw(card)
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
        val card1 = Card.of(NumberRank.SEVEN, Suit.HEART)
        val card2 = Card.of(NumberRank.SEVEN, Suit.DIAMOND)
        val card3 = Card.of(NumberRank.SEVEN, Suit.DIAMOND)
        player.draw(card1)
        println(player.score)
        println(player.cards)
        player.draw(card2)
        println(player.score)
        println(player.cards)
        player.draw(card3)
        println(player.score)
        println(player.cards)
        assertThrows<IllegalArgumentException> {
            player.draw(Card.of(NumberRank.TWO, Suit.SPADE))
        }
    }

    @Test
    fun `플레이어 카드의 합이 21 이하가 될 수 없는 플레이어는 반드시 패배한다`() {
        val player = Player("Eden")
        player.draw(
            Card.of(FaceRank.JACK, Suit.DIAMOND),
            Card.of(FaceRank.JACK, Suit.DIAMOND),
            Card.of(FaceRank.JACK, Suit.DIAMOND),
        )
        player.score
        assertThat(player.state).isEqualTo(ParticipantState.LOSE)
    }

    @Test
    fun `플레이어의 최종 결과를 알 수 있다`() {
        val player =
            Player("Gio").apply {
                draw(Card.of(NumberRank.TEN, Suit.DIAMOND))
                draw(Card.of(NumberRank.TEN, Suit.DIAMOND))
                draw(Card.of(NumberRank.TEN, Suit.DIAMOND))
            }
        player.score
        assertThat(player.state).isEqualTo(ParticipantState.LOSE)
    }
}
