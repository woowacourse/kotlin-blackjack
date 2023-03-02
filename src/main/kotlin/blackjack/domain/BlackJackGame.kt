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
        when (guest.getCommand()) {
            in DRAW_COMMANDS -> draw(guest, output)
            else -> output(guest)
        }
    }

    private fun BlackJack.draw(guest: Guest, output: (User) -> Unit) {
        guest.draw(cardDeck.nextCard())
        output(guest)
        if (guest.isContinue) {
            guestTurn(guest, output)
        }
    }

    private fun Guest.getCommand() = input(this.name.toString())

    companion object {
        const val BLACKJACK_NUMBER = 21
        private val DRAW_COMMANDS = listOf("Y", "y")
    }
}
