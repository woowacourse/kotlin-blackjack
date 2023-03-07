package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    @Test
    fun `블랙잭 게임 세팅 - 해시라는 유저를 생성한다`() {
        // given
        val blackJackGame = BlackJackGame()
        val userNames = listOf<String>("해시")

        // when
        blackJackGame.setUpBlackJackGame { userNames }

        // then
        assertThat(blackJackGame.players.users[0].name).isEqualTo("해시")
    }
}
