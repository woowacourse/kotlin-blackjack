package blackjack

import blackjack.CardFixture.Companion.HEART_NINE
import blackjack.CardFixture.Companion.HEART_QUEEN
import blackjack.CardFixture.Companion.HEART_SEVEN
import blackjack.model.Money
import blackjack.model.user.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("미플", Money.from(1_000L))
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        assertThat(player.name).isEqualTo("미플")
    }

    @Test
    fun `플레이어는 카드 총 합이 21을 넘으면 true를 반환한다`() {
        player.addCard(HEART_NINE)
        player.addCard(HEART_QUEEN)
        player.addCard(HEART_SEVEN)
        val expected = true

        val actual = player.isBust()

        assertThat(actual).isEqualTo(expected)
    }
}
