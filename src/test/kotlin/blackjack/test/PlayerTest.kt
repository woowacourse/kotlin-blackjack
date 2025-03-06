package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class Player(
    val name: String,
) {
    val hand: Hand = Hand(emptyList())
    var wantToHit: Boolean? = null
    var result: Result = Result.NOT_YET

    fun getMoreCard(card: Card) {
        hand.add(card)
    }

    fun getCountOfCards(): Int = hand.getSize()

    fun hitOrStay(hit: () -> Unit) {
        if (wantToHit == true) {
            hit()
        }
    }

    fun setResult() {
        if (hand.getScore() == null || hand.getScore()!! > 21) {
            result = Result.LOSE
        }
    }
}

enum class Result {
    WIN,
    DRAW,
    LOSE,
    NOT_YET,
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

    @Test
    fun `플레이어 카드의 합이 21 이하가 될 수 없는 플레이어는 반드시 패배한다`() {
        val player = Player(name = "Eden")
        player.getMoreCard(Card(Character.JACK, Suit.DIAMOND))
        player.getMoreCard(Card(Character.JACK, Suit.HEART))
        player.getMoreCard(Card(Character.JACK, Suit.SPADE))
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
