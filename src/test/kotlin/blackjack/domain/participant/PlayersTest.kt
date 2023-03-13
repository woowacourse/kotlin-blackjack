package blackjack.domain.participant

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `2명 이상 8명 이하가 아닌 플레이어들을 생성하려하면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy { Players(listOf(Player("pobi"))) }
            .withMessage("플레이어의 수는 2명 이상 8명 이하여야 합니다.")
    }

    @Test
    fun `중복된 플레이어가 있는 플레이어들을 생성하려하면 중복이 제거된 플레이어들이 생성된다`() {
        val players = listOf(Player("pobi"), Player("pobi"), Player("thomas"))

        val actual = Players(players)

        assertThat(actual.toList()).containsExactlyInAnyOrder(Player("pobi"), Player("thomas"))
    }

    @Test
    fun `서로 다른 2명 이상 8명 이하의 플레이어로 플레이어들을 생성할 수 있다`() {
        assertDoesNotThrow { Players(listOf(Player("pobi"), Player("thomas"))) }
    }
}
