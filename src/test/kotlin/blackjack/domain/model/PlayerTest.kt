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
    fun `플레이어는 입력 받은 숫자 만큼 반환한다 `() {
        assertThat(player.showCards(6)).isEqualTo(listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `플레이어는 카드를 받는다`() {
        val actual =
            Player(
                "동전",
                Card(Suit.HEART, Rank.ACE),
                Card(Suit.HEART, Rank.KING),
            )
        player.acceptCard(Card(Suit.HEART, Rank.KING))
        assertThat(player.showCards()).isEqualTo(actual)
    }
}
