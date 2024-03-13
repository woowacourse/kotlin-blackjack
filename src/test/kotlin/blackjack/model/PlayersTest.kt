package blackjack.model

import blackjack.model.card.CardHand
import blackjack.model.role.PlayerName
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class PlayersTest {
    @Test
    fun `플레이어들 이름에 중복을 허용하지 않는다`() {
        assertThatThrownBy {
            Players(
                listOf(
                    Player(PlayerName("심지"), CardHand(emptyList())),
                    Player(PlayerName("심지"), CardHand(emptyList())),
                    Player(PlayerName("해나"), CardHand(emptyList())),
                ),
            )
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("플레이어 이름에 중복이 있습니다.")
    }

    @Test
    fun `플레이어들 이름에 중복이 존재하지 않는다`() {
        assertDoesNotThrow {
            Players(
                listOf(
                    Player(PlayerName("심지"), CardHand(emptyList())),
                    Player(PlayerName("해나"), CardHand(emptyList())),
                ),
            )
        }
    }

    @Test
    fun `플레이어는 7명 이상일 수 없다`() {
        assertThatThrownBy {
            Players(
                listOf(
                    Player(PlayerName("심지"), CardHand(emptyList())),
                    Player(PlayerName("해나"), CardHand(emptyList())),
                    Player(PlayerName("악어"), CardHand(emptyList())),
                    Player(PlayerName("팡태"), CardHand(emptyList())),
                    Player(PlayerName("채채"), CardHand(emptyList())),
                    Player(PlayerName("서기"), CardHand(emptyList())),
                    Player(PlayerName("벼리"), CardHand(emptyList())),
                    Player(PlayerName("올리브"), CardHand(emptyList())),
                ),
            )
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("플레이어의 수는 1명 이상, 6명 이하여야 합니다.")
    }
}
