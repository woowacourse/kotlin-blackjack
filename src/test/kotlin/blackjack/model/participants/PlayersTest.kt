package blackjack.model.participants

import blackjack.model.gameInfo.GameInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayersTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 9])
    fun `참여자 수가 허용 범위를 벗어나면, 예외를 발생시킨다`(numberOfPlayers: Int) {
        val exception =
            org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
                Players(List(numberOfPlayers) { Player(GameInfo("player$it")) { "y" } })
            }
        assertThat(exception.message).isEqualTo("플레이어의 수로 ${numberOfPlayers}를 입력했습니다. 플레이어 수는 2부터 8까지 가능합니다.")
    }

    @Test
    fun `참여자 이름이 중복이 있으면, 예외를 발생시킨다`() {
        val players = listOf("케이엠", "케이엠", "해음")
        val exception =
            org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
                val playersData = Players(players.map { Player(GameInfo("player")) { "y" } })
                println(playersData.value)
            }
        assertThat(exception.message).isEqualTo("중복된 플레이어의 이름을 입력하셨습니다.")
    }
}
