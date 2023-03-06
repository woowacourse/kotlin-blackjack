package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class BlackJackGameTest {
    @Test
    fun `게임을 실행한다`() {
        val blackJack = blackJack {
            cardDeck(Cards.all())
            participants {
                dealer()
                guests(listOf("아크", "로피"))
            }
            draw()
        }

        assertDoesNotThrow {
            BlackJackGame().apply {
                getCommand = ::inputDrawMore
                guestsTurn(blackJack.guests, blackJack.cardDeck, ::outputCard)
                dealerTurn(blackJack.dealer, blackJack.cardDeck, ::outputDealer)
            }
        }
    }

    private fun inputDrawMore(string: String): String {
        return "y"
    }

    private fun outputCard(user: User) = null

    private fun outputDealer() = null
}
