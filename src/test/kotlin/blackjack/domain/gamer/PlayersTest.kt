package blackjack.domain.gamer

import blackjack.state.Hit
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PlayersTest {

    @Test
    @DisplayName("hit을 할 수 있는 플레이어가 있는지 확인한다.")
    fun isRemainToHit() {
        val players = Players(listOf(Player("ama"), Player("maz")))

        val result = players.isRemainToHit()

        assertThat(result).isFalse
    }
}