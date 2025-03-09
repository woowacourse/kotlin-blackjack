package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun `setUp`() {
        player = Player("동전", Card(Suit.HEART, Rank.ACE))
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        assertThat(player.name).isEqualTo("동전")
    }

    @Test
    fun `플레이어는 카드를 가진다`() {
        assertThat(player.hands.showCards()).isEqualTo(listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `플레이어는 카드를 받는다`() {
        val actual =
            Player(
                "동전",
                Card(Suit.HEART, Rank.ACE),
                Card(Suit.HEART, Rank.KING),
            )
        player.hands.accept(listOf(Card(Suit.HEART, Rank.KING)))
        assertThat(player.hands.showCards()).isEqualTo(actual.hands.showCards())
    }
}
