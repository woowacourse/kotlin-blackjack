package blackjack.domain

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
    fun `중복된 플레이어가 있는 플레이어들을 생성하려하면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy { Players(listOf(Player("pobi"), Player("pobi"))) }
            .withMessage("중복된 플레이어가 존재합니다.")
    }

    @Test
    fun `서로 다른 2명 이상 8명 이하의 플레이어로 플레이어들을 생성할 수 있다`() {
        assertDoesNotThrow { Players(listOf(Player("pobi"), Player("thomas"))) }
    }
}
