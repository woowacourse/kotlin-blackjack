package blackjack.domain

class BlackJackGame {
    private lateinit var input: (String) -> String

    fun input(func: (String) -> String) { input = func }

    fun BlackJack.guestsTurn(output: (User) -> Unit) {
        guests.forEach { guest ->
            while (guest.isContinue) {
                command(guest, cardDeck)
                output(guest)
            }
        }
    }

    fun BlackJack.dealerTurn(output: () -> Unit) {
        if (dealer.isContinue) {
            dealer.draw(cardDeck.nextCard())
            output()
        }
    }

    private fun command(guest: Guest, cardDeck: CardDeck) {
        if (guest.isDrawCommand()) return
        guest.draw(cardDeck.nextCard())
    }

    private fun Guest.isDrawCommand() = input(this.name.toString()) !in DRAW_COMMANDS

    companion object {
        const val BLACKJACK_NUMBER = 21
        private val DRAW_COMMANDS = listOf("Y", "y")
    }
}
