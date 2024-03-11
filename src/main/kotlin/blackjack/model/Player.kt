package blackjack.model

class Player(val name: String, hand: Hand) : Participant(hand) {
    constructor(pair: Pair<String, Hand>) : this(pair.first, pair.second)

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
            hands: List<Hand>,
        ): List<Player> {
            return names.zip(hands).map(::Player)
        }
    }
}
