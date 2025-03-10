package blackjack.domain

import blackjack.domain.Rank.FaceRank
import blackjack.domain.Rank.NumberRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    @Test
    fun `플레이어는 이름으로 구분된다`() {
        val player = Player(name = "Eden")
        assertThat(player.name).isEqualTo("Eden")
    }

    @Test
    fun `플레이어는 모든 카드의 합이 21 미만이 될 수 있을 경우 계속해서 카드를 뽑을 수 있다`() {
        val player = Player(name = "Eden")
        val card1 = Card(NumberRank.SEVEN, Suit.HEART)
        val card2 = Card(NumberRank.SEVEN, Suit.DIAMOND)
        val card3 = Card(NumberRank.SEVEN, Suit.DIAMOND)
        player.draw(card1)
        player.draw(card2)
        player.draw(card3)

        assertThrows<IllegalArgumentException> { player.draw(Card(NumberRank.TWO, Suit.SPADE)) }
    }

    @Test
    fun `플레이어 카드의 합이 21 이하가 될 수 없는 플레이어는 반드시 패배한다`() {
        val player = Player(name = "Eden")
        player.draw(Card(FaceRank.JACK, Suit.DIAMOND))
        player.draw(Card(FaceRank.JACK, Suit.HEART))
        player.draw(Card(FaceRank.JACK, Suit.SPADE))
        assertThat(player.cards.size).isEqualTo(3)
        player.setResult()
        assertThat(player.playerState).isEqualTo(PlayerState.LOSE)
    }

    @Test
    fun `플레이어의 최종 결과를 알 수 있다`() {
        val player = Player("Gio")
        player.playerState = PlayerState.LOSE
        assertThat(player.playerState).isEqualTo(PlayerState.LOSE)
    }
}
