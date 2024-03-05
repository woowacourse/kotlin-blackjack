package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DealerTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 9])
    fun `참여자 수가 허용 범위를 벗어나면, 예외를 발생시킨다`(numberOfPlayers: Int) {
        val exception =
            assertThrows<IllegalArgumentException> {
                Dealer(numberOfPlayers)
            }
        assertThat(exception.message).isEqualTo("플레이어의 수로 ${numberOfPlayers}를 입력했습니다. 플레이어 수는 2부터 8까지 가능합니다.")
    }
}
