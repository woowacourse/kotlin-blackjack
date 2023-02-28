package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    @Test
    fun `초기세팅이 된다`() {
        // given
        val names = listOf("아크", "로피")
        // when
        val blackJackGame = BlackJackGame(names)
        blackJackGame.setUp()

        // then
        assertThat(blackJackGame.dealer.cards.size).isEqualTo(2)
        blackJackGame.users.forEach { user ->
            assertThat(user.cards.size).isEqualTo(2)
        }
    }

    @Test
    fun `y를 누를 누르면 한장이 뽑아진다`() {
        // given
        val names = listOf("아크", "로피")
        // when
        val blackJackGame = BlackJackGame(names)
        val user = blackJackGame.setUp()
        blackJackGame.progress(user, "y")

        // then
        assertThat(user.cards.size).isEqualTo(3)
    }

    @Test
    fun `n을 누르면 다음 사람 차례다`() {
        // given
        val names = listOf("아크", "로피")
        // when
        val blackJackGame = BlackJackGame(names)
        val user = blackJackGame.setUp()
        blackJackGame.progress(user, "n")

        assertThat(blackJackGame.userIndex).isEqualTo(1)
    }
}
