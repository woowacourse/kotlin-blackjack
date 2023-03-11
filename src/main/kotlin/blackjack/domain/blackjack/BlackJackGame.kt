package blackjack.domain.blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.participants.user.Dealer
import blackjack.domain.participants.user.Guest
import blackjack.domain.participants.user.Money
import blackjack.domain.participants.user.Name
import blackjack.domain.participants.user.User
import blackjack.domain.state.endTurn.BlackJack

class BlackJackGame {
    var onDraw: (String) -> Boolean = { true }

    fun setUp(getNames: () -> List<Name>, getBettingMoney: (String) -> Money): BlackJackSetting =
        blackJack {
            participants {
                dealer()
                getNames().forEach { name -> guest(name, getBettingMoney(name.toString())) }
            }
            initDrawAll()
        }

    fun dealerTurn(dealer: Dealer, cardDeck: CardDeck, output: () -> Unit) {
        when {
            dealer.state is BlackJack -> return
            dealer.isContinuable -> dealer.draw(cardDeck.drawCard())
        }
        output()
    }

    fun guestsTurn(guests: List<Guest>, cardDeck: CardDeck, output: (User) -> Unit) =
        guests.forEach { guest -> guestTurn(guest, cardDeck, output) }

    private fun guestTurn(guest: Guest, cardDeck: CardDeck, output: (User) -> Unit) {
        when (onDraw(guest.name.toString())) {
            (guest.state is BlackJack) -> return
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
