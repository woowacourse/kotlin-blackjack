package blackjack.domain.blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.participants.* // ktlint-disable no-wildcard-imports

class BlackJackGame {
    lateinit var getCommand: (String) -> Boolean

    fun guestsTurn(guests: List<Guest>, cardDeck: CardDeck, output: (User) -> Unit) =
        guests.forEach { guest -> guestTurn(guest, cardDeck, output) }

    fun dealerTurn(dealer: Dealer, cardDeck: CardDeck, output: () -> Unit) {
        if (dealer.isBlackJack()) return
        if (dealer.isContinue) {
            dealer.draw(cardDeck.nextCard())
            output()
        }
    }

    private fun guestTurn(guest: Guest, cardDeck: CardDeck, output: (User) -> Unit) {
        if (guest.isBlackJack()) return
        if (getCommand(guest.name.toString())) draw(guest, cardDeck, output) else output(guest)
    }

    private fun draw(guest: Guest, cardDeck: CardDeck, output: (User) -> Unit) {
        guest.draw(cardDeck.nextCard())
        output(guest)
        if (guest.isContinue) {
            guestTurn(guest, cardDeck, output)
        }
    }
}
