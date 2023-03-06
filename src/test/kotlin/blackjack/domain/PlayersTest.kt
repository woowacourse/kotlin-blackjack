package blackjack.domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `플레이어는 최소 1명이어야 한다`() {
        val players = emptyList<Player>()

        assertThatIllegalArgumentException().isThrownBy { Players(players) }
            .withMessageContaining("블랙잭은 최소 1명에서 최대 7명의 플레이어가 참여 가능합니다. (현재 플레이어수 : 0명)")
    }

    @Test
    fun `플레이어는 최대 7명까지 가능하다`() {
        val players = listOf(
            Player("a"), Player("b"),
            Player("c"), Player("d"),
            Player("e"), Player("f"),
            Player("g"), Player("h")
        )

        assertThatIllegalArgumentException().isThrownBy { Players(players) }
            .withMessageContaining("블랙잭은 최소 1명에서 최대 7명의 플레이어가 참여 가능합니다. (현재 플레이어수 : 8명)")
    }

    @Test
    fun `모든 플레이어의 카드를 2장씩 뽑는다`() {
        val deck = CardDeck(
            listOf(
                Card(Suit.SPADE, CardNumber.ACE), Card(Suit.HEART, CardNumber.ACE),
                Card(Suit.DIAMOND, CardNumber.ACE), Card(Suit.SPADE, CardNumber.KING),
                Card(Suit.SPADE, CardNumber.TWO), Card(Suit.SPADE, CardNumber.EIGHT)
            )
        )
        val players = Players(listOf(Player("buna"), Player("glo"), Player("bandal")))

        players.drawAll(deck)
        players.drawAll(deck)

        val actual = (0 until 3).flatMap { players[it].getHand().hand }
        assertEquals(
            actual,
            listOf("A스페이드", "K스페이드", "A다이아몬드", "2스페이드", "A하트", "8스페이드")
        )
    }
}
