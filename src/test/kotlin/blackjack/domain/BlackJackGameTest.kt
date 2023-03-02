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
            draw()
        }

        assertDoesNotThrow {
            BlackJackGame().apply {
                input(::inputDrawMore)
                blackJack.usersTurn(::outputCard)
                blackJack.dealerTurn(::outputDealer)
            }
        }
    }

    private fun inputDrawMore(string: String): String {
        return "y"
    }

    private fun outputCard(user: User) = null
    private fun outputDealer() = null
}
