package blackjack.domain

class BlackJackGame {
    private lateinit var input: (String) -> String

    fun input(func: (String) -> String) { input = func }

    fun BlackJack.guestsTurn(output: (User) -> Unit) =
        guests.forEach { guest -> guestTurn(guest, output) }

    fun BlackJack.dealerTurn(output: () -> Unit) {
        if (dealer.isContinue) {
            dealer.draw(cardDeck.nextCard())
            output()
        }
    }

    private fun BlackJack.guestTurn(guest: Guest, output: (User) -> Unit) {
        when (getCommand(guest.name)) {
            in DRAW_COMMANDS -> draw(guest, output)
            in END_TURN_COMMANDS -> output(guest)
            else -> this.guestTurn(guest, output)
        }
    }

    private fun BlackJack.draw(guest: Guest, output: (User) -> Unit) {
        guest.draw(cardDeck.nextCard())
        output(guest)
        if (guest.isContinue) {
            guestTurn(guest, output)
        }
    }

    private fun getCommand(name: Name) = input(name.toString())

    companion object {
        const val BLACKJACK_NUMBER = 21
        private val DRAW_COMMANDS = listOf("Y", "y")
        private val END_TURN_COMMANDS = listOf("N", "n")
    }
}
