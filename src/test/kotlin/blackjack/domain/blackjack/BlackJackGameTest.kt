package blackjack.domain.blackjack

import blackjack.domain.participants.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class BlackJackGameTest {
    @Test
    fun `게임을 실행한다`() {
        val blackJack = blackJack {
            participants {
                dealer()
                guest("아크", 10)
                guest("로피", 10)
            }
            draw()
        }

        assertDoesNotThrow {
            BlackJackGame().apply {
                guestsTurn(blackJack.guests, blackJack.cardDeck, ::outputCard)
                dealerTurn(blackJack.dealer, blackJack.cardDeck, ::outputDealer)
            }
        }
    }

    private fun outputCard(user: User) = null

    private fun outputDealer() = null
}
