package blackjack.model

class Player(val name: String, hand: Hand) : Participant(hand) {
    fun comparePoints(dealer: Dealer): WinningState {
        val playerCards = hand
        val dealerCards = dealer.hand
        if (playerCards.isBust()) return WinningState.LOSS
        if (dealerCards.isBust()) return WinningState.WIN
        val compared = playerCards.sumOptimized() compareTo dealerCards.sumOptimized()
        return WinningState.from(compared)
    }

    companion object {
        fun createPlayers(
            names: List<String>,
            deck: Deck,
        ): List<Player> {
            val players = names.map { Player(it, Hand(listOf())) }
            players.map { it.initialSetHand(deck) }
            return players
        }
    }
}
