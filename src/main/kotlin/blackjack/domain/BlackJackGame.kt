package blackjack.domain

class BlackJackGame {
    lateinit var getCommand: (String) -> String

    fun guestsTurn(guests: List<Guest>, cardDeck: CardDeck, output: (User) -> Unit) =
        guests.forEach { guest -> guestTurn(guest, cardDeck, output) }

    fun dealerTurn(dealer: Dealer, cardDeck: CardDeck, output: () -> Unit) {
        if (dealer.isBlackJack) return
        if (dealer.isContinue) {
            dealer.draw(cardDeck.nextCard())
            output()
        }
    }

    private fun guestTurn(guest: Guest, cardDeck: CardDeck, output: (User) -> Unit) {
        if (guest.isBlackJack) return
        when (getCommand(guest.name.toString())) {
            in DRAW_COMMANDS -> draw(guest, cardDeck, output)
            in END_TURN_COMMANDS -> output(guest)
            else -> this.guestTurn(guest, cardDeck, output)
        }
    }

    private fun draw(guest: Guest, cardDeck: CardDeck, output: (User) -> Unit) {
        guest.draw(cardDeck.nextCard())
        output(guest)
        if (guest.isContinue) {
            guestTurn(guest, cardDeck, output)
        }
    }

    companion object {
        private val DRAW_COMMANDS = listOf("Y", "y")
        private val END_TURN_COMMANDS = listOf("N", "n")
    }
}
