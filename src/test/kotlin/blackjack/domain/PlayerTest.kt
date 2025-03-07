package blackjack.domain

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
        val card1 = Card(Number(7), Suit.HEART)
        val card2 = Card(Number(7), Suit.DIAMOND)
        val card3 = Card(Number(7), Suit.DIAMOND)
        player.getCards(listOf(card1, card2, card3))
        assertThrows<IllegalArgumentException> { player.getCard(Card(Number(2), Suit.SPADE)) }
    }

    @Test
    fun `플레이어 카드의 합이 21 이하가 될 수 없는 플레이어는 반드시 패배한다`() {
        val player = Player(name = "Eden")
        player.getCard(Card(Character.JACK, Suit.DIAMOND))
        player.getCard(Card(Character.JACK, Suit.HEART))
        player.getCard(Card(Character.JACK, Suit.SPADE))
        assertThat(player.getCountOfCards()).isEqualTo(3)
        player.setResult()
        assertThat(player.result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `플레이어의 최종 결과를 알 수 있다`() {
        val player = Player("Gio")
        player.result = Result.LOSE
        assertThat(player.result).isEqualTo(Result.LOSE)
    }
}
