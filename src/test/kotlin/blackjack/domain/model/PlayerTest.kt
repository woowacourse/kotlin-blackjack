package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun `setUp`() {
        player = Player("동전", cards = listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        assertThat(player.name).isEqualTo("동전")
    }

    @Test
    fun `플레이어는 카드를 가진다`() {
        assertThat(player.cards).isEqualTo(listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `플레이어는 카드를 받는다`() {
        assertThat(player.accept(Card(Suit.HEART, Rank.KING))).isEqualTo(
            player.copy(
                cards =
                    listOf(
                        Card(
                            Suit.HEART,
                            Rank.ACE,
                        ),
                        Card(Suit.HEART, Rank.KING),
                    ),
            ),
        )
    }

    @Test
    fun `카드의 총합을 반환한다`() {
        player = Player("동전", cards = listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING)))
        assertThat(player.getScore()).isEqualTo(11)
    }
}
