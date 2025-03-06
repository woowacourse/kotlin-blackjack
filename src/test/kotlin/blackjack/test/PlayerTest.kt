package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class Player(
    val name: String,
) {
    val hand: Hand = Hand(emptyList())

    fun getMoreCard(card: Card) {
        hand.add(card)
    }

    fun getCountOfCards(): Int = hand.getSize()
}

class PlayerTest {
    @Test
    fun `플레이어는 이름으로 구분된다`() {
        val player = Player(name = "Eden")
        assertThat(player.name).isEqualTo("Eden")
    }

    @Test
    fun `플레이어는 모든 카드의 합이 21 미만이 될 수 있을 경우 계속해서 카드를 뽑을 수 있다`() {
        val player = Player(name = "Eden")
        val card = Card(Number(10), Suit.HEART)
        val card2 = Card(Ace(), Suit.DIAMOND)
        player.getMoreCard(card)
        player.getMoreCard(card2)
        assertThrows<IllegalArgumentException> { player.getMoreCard(Card(Number(2), Suit.SPADE)) }
    }
}
