package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PlayersTest {
    @Test
    fun `게임에 참여할 플레이어는 1명 이상이다`() {
        val card1 = Card(CardRank.ACE, Shape.CLUB)
        val card2 = Card(CardRank.SIX, Shape.CLUB)
        val jayCards = mutableListOf(card1, card2)

        val card3 = Card(CardRank.NINE, Shape.CLUB)
        val card4 = Card(CardRank.FIVE, Shape.CLUB)
        val joyCards = mutableListOf(card3, card4)

        val jay = Player("jay", Cards(jayCards))
        val joy = Player("joy", Cards(joyCards))

        assertDoesNotThrow {
            Players(listOf(joy, jay))
        }
    }

    @Test
    fun `게임에 참여할 플레이어는 중복될 수 없다`() {
        val card1 = Card(CardRank.ACE, Shape.CLUB)
        val card2 = Card(CardRank.SIX, Shape.CLUB)
        val jayCards = mutableListOf(card1, card2)

//        val card3 = Card(CardRank.NINE, Shape.CLUB)
//        val card4 = Card(CardRank.FIVE, Shape.CLUB)
//        val joyCards = mutableListOf(card3, card4)

        val jay1 = Player("jay", Cards(jayCards))
        val jay2 = Player("jay", Cards(jayCards))

        assertThrows<IllegalArgumentException> { Players(listOf(jay1, jay2)) }
    }
}
