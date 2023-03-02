package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {

    @Test
    fun `이름을 생성할 수 있다`() {
        assertDoesNotThrow { TestPlayer("수달") }
    }

    @Test
    fun `이름은 2글자 이상 10글자 이하여야 한다`() {
        assertDoesNotThrow { TestPlayer("berry") }
    }

    @ParameterizedTest
    @ValueSource(strings = ["b", "berryyyyyyy"])
    fun `이름은 2글자 미만이거나 10글자 초과면 오류가 발생한다`(expected: String) {
        assertThrows<IllegalArgumentException> { TestPlayer(expected) }
    }

    @Test
    fun `카드들을 가진다`() {
        val player = TestPlayer("aa")
        assertThat(player.cards.values).isEqualTo(Cards().values)
    }
    class TestPlayer(name: String) : Player(name)
}
