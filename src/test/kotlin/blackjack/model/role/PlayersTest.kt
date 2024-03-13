package blackjack.model.role

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PlayersTest {
    @Test
    fun `플레이어들 이름에 중복이 없고 딜러를 제외한 수가 1명 이상 6명 이하여야 한다`() {
        assertDoesNotThrow {
            Players(
                listOf(
                    Player(PlayerName("심지")),
                    Player(PlayerName("해나")),
                    Player(PlayerName("팡태")),
                    Player(PlayerName("악어")),
                    Player(PlayerName("올리브")),
                    Player(PlayerName("서기")),
                )
            )
        }
    }

    @Test
    fun `플레이어들의 이름에 중복이 있으면 안된다`() {
        assertThrows<IllegalArgumentException> {
            Players(
                listOf(
                    Player(PlayerName("심지")),
                    Player(PlayerName("해나")),
                    Player(PlayerName("심지")),
                )
            )
        }
    }

    @Test
    fun `딜러를 제외한 플레이어들의 수가 1명 미만이면 안된다`() {
        assertThrows<IllegalArgumentException> {
            Players(listOf())
        }
    }

    @Test
    fun `딜러를 제외한 플레이어들의 수가 6명 초과이면 안된다`() {
        assertThrows<IllegalArgumentException> {
            Players(
                listOf(
                    Player(PlayerName("심지")),
                    Player(PlayerName("해나")),
                    Player(PlayerName("팡태")),
                    Player(PlayerName("악어")),
                    Player(PlayerName("올리브")),
                    Player(PlayerName("서기")),
                    Player(PlayerName("채채")),
                )
            )
        }
    }
}
