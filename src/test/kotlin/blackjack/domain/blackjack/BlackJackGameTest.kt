package blackjack.domain.blackjack

import blackjack.domain.participants.user.Name
import blackjack.domain.participants.user.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class BlackJackGameTest {
    @Test
    fun `게임을 실행한다`() {
        val data = blackJackData {
            participants {
                dealer()
                guest(Name("아크"))
                guest(Name("로피"))
            }
            initDrawAll()
        }

        assertDoesNotThrow {
            BlackJackGame().apply {
                guestsTurn(data.guests, data.cardDeck, ::outputCard)
                dealerTurn(data.dealer, data.cardDeck, ::outputDealer)
            }
        }
    }

    private fun outputCard(user: User) = null

    private fun outputDealer() = null
}
