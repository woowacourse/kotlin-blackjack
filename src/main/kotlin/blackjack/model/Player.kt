package blackjack.model

class Player(val name: String, val hand: Hand) {
    constructor(pair: Pair<String, Hand>) : this(pair.first, pair.second)

    fun hit(card: Card) {
        hand.add(card)
    }

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
