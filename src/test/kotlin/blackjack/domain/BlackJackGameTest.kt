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
}
