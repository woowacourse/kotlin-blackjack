package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class BlackJackGameTest {
    @Test
    fun `게임을 실행한다`() {
        val blackJack = BlackJackBuilder.init {
            cardDeck(Card.all().shuffled())
            participants {
                dealer("딜러")
                users(listOf("아크", "로피"))
            }
        }

        assertDoesNotThrow {
            BlackJackGame().apply {
                input(::inputDrawMore)
                output(::outputCard)
                run(blackJack)
            }
        }
    }

    private fun inputDrawMore(string: String): String {
        return "y"
    }

    private fun outputCard(user: User) = null
}
