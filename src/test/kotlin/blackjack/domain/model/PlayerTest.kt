package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("동전", listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        assertThat(player.name).isEqualTo("동전")
    }

    @Test
    fun `플레이어는 카드를 가진다`() {
        assertThat(player.showHand()).isEqualTo(listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `플레이어는 카드를 받는다`() {
        val actual =
            Player(
                "동전",
                listOf(
                    Card(Suit.HEART, Rank.ACE),
                    Card(Suit.HEART, Rank.KING),
                ),
            )
        player.accept(listOf(Card(Suit.HEART, Rank.KING)))
        assertThat(player.showHand()).isEqualTo(actual.showHand())
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "\t", "\n"])
    fun `플레이어의 이름이 공백일 시 오류가 발생한다`(value: String) {
        assertThrows<IllegalArgumentException> { Player(value) }
    }
}
