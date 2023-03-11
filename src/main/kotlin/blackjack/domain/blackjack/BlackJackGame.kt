package blackjack.domain.blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.participants.user.Dealer
import blackjack.domain.participants.user.Guest
import blackjack.domain.participants.user.Money
import blackjack.domain.participants.user.Name
import blackjack.domain.participants.user.User

class BlackJackGame {
    var onDraw: (String) -> Boolean = { true }

    fun setUp(getNames: () -> List<Name>, getBettingMoney: (String) -> Money): BlackJackSetting =
        blackJackSetting {
            participants {
                dealer()
                getNames().forEach { name -> guest(name, getBettingMoney(name.toString())) }
            }
            initDrawAll()
        }

    fun dealerTurn(dealer: Dealer, cardDeck: CardDeck, output: () -> Unit) {
        if (dealer.isHit) dealer.draw(cardDeck.drawCard())
        output()
    }

    fun guestsTurn(guests: List<Guest>, cardDeck: CardDeck, output: (User) -> Unit) =
        guests.forEach { guest -> guestTurn(guest, cardDeck, output) }

    private fun guestTurn(guest: Guest, cardDeck: CardDeck, output: (User) -> Unit) {
        if (guest.isHit.not()) return
        when (onDraw(guest.name.toString())) {
            true -> guestDraw(guest, cardDeck, output)
            false -> output(guest)
        }
    }

    private fun guestDraw(guest: Guest, cardDeck: CardDeck, output: (User) -> Unit) {
        guest.draw(cardDeck.drawCard())
        output(guest)
        if (guest.isHit) { guestTurn(guest, cardDeck, output) }
    }
}
