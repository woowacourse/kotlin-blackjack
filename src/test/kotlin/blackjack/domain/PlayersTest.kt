package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `모든 플레이어의 카드를 2장씩 뽑는다`() {
        val deck = CardDeck(TestCardGenerator(mutableListOf(1, 14, 27, 13, 2, 8)))
        val players = Players(listOf(Player("buna"), Player("glo"), Player("bandal")))

        players.drawAll(deck)
        players.drawAll(deck)

        val actual = (0 until 3).flatMap { players[it].getCardNames() }
        assertEquals(
            actual,
            listOf("A스페이드", "K스페이드", "A다이아몬드", "2스페이드", "A하트", "8스페이드")
        )
    }

    class TestCardGenerator(private val numbers: MutableList<Int>) : CardGenerator {
        override fun generate(): Card = Card.of(numbers.removeAt(0))
    }
}
