package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class Player(
    val name: String,
    val cards: Cards,
) {
    fun getMoreCard(card: Card) {
        cards.add(card)
    }
}

class PlayerTest {
    @Test
    fun `플레이어는 이름으로 구분된다`() {
        val player = Player(name = "Eden", cards = Cards(emptyList()))
        assertThat(player.name).isEqualTo("Eden")
    }

    @Test
    fun `플레이어는 모든 카드의 합이 21 미만이 될 수 있을 경우 계속해서 카드를 뽑을 수 있다`() {
        val player =
            Player(
                name = "Eden",
                cards =
                    Cards(
                        listOf(
                            Card(Character.QUEEN, Suit.SPADE),
                            Card(Character.KING, Suit.HEART),
                            Card(Ace(), Suit.HEART),
                        ),
                    ),
            )
        assertThrows<IllegalArgumentException> { player.getMoreCard(Card(Number(2), Suit.SPADE)) }
    }
}

class Car
