package blackjack.domain.blackjack

import blackjack.domain.participants.Name
import blackjack.domain.participants.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class BlackJackSettingGameTest {
    @Test
    fun `게임을 실행한다`() {
        val blackJack = blackJack {
            participants {
                dealer()
                guest(Name("아크"))
                guest(Name("로피"))
            }
            initDrawAll()
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
