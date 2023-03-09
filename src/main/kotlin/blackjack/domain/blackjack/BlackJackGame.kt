package blackjack.domain.blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Guest
import blackjack.domain.participants.Money
import blackjack.domain.participants.Name
import blackjack.domain.participants.User

class BlackJackGame {
    var onDraw: (String) -> Boolean = { true }

    fun setUp(getNames: () -> List<Name>, getBettingMoney: (String) -> Money): BlackJack =
        blackJack {
            participants {
                dealer()
                getNames().forEach { name -> guest(name, getBettingMoney(name.toString())) }
            }
            initDrawAll()
        }

    fun dealerTurn(dealer: Dealer, cardDeck: CardDeck, output: () -> Unit) {
        if (dealer.isBlackJack) return
        if (dealer.isContinuable) {
            dealer.draw(cardDeck.drawCard())
            output()
        }
    }

    fun guestsTurn(guests: List<Guest>, cardDeck: CardDeck, output: (User) -> Unit) =
        guests.forEach { guest -> guestTurn(guest, cardDeck, output) }

    private fun guestTurn(guest: Guest, cardDeck: CardDeck, output: (User) -> Unit) {
        if (guest.isBlackJack) return
        when (onDraw(guest.name.toString())) {
            true -> guestDraw(guest, cardDeck, output)
            false -> output(guest)
        }
    }

    private fun guestDraw(guest: Guest, cardDeck: CardDeck, output: (User) -> Unit) {
        guest.draw(cardDeck.drawCard())
        output(guest)
        if (guest.isContinuable) {
            guestTurn(guest, cardDeck, output)
        }
    }
}
