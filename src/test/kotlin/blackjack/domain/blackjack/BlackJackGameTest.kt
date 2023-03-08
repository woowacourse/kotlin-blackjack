package blackjack.domain.blackjack

import blackjack.domain.card.Cards
import blackjack.domain.participants.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class BlackJackGameTest {
    @Test
    fun `게임을 실행한다`() {
        val blackJack = blackJack {
            participants {
                dealer()
                guests(listOf("아크", "로피"))
            }
            initDraw()
        }

        assertDoesNotThrow {
            BlackJackGame().apply {
                getCommand = ::inputDrawMore
                guestsTurn(blackJack.guests, blackJack.cardDeck, ::outputCard)
                dealerTurn(blackJack.dealer, blackJack.cardDeck, ::outputDealer)
            }
        }
    }

    private fun inputDrawMore(string: String): Boolean {
        return true
    }

    private fun outputCard(user: User) = null

    private fun outputDealer() = null
}
