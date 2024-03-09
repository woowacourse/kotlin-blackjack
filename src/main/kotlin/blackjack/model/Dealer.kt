package blackjack.model

class Dealer(hand: Hand) {
    private val _hand: MutableList<Card> = hand.cards.toMutableList()
    val hand: Hand get() = Hand(_hand.toList())

    fun canHit(): Boolean {
        if (hand.isBust()) return false
        return hand.sumOptimized() < HIT_CONDITION
    }

    fun hit(card: Card) {
        _hand.add(card)
    }

    fun hitUntilBust(deck: Deck) {
        while (canHit()) {
            hit(deck.pull())
        }
    }

    fun createScoreBoard(players: List<Player>): ScoreBoard {
        val results: List<PlayerResult> =
            players.map { player ->
                PlayerResult(player.name, player.comparePoints(this))
            }
        return ScoreBoard.from(results)
    }

    private fun Player.comparePoints(dealer: Dealer): WinningState {
        val playerCards = hand
        val dealerCards = dealer.hand
        if (playerCards.isBust()) return WinningState.LOSS
        if (dealerCards.isBust()) return WinningState.WIN
        val compared = playerCards.sumOptimized() compareTo dealerCards.sumOptimized()
        return WinningState.from(compared)
    }

    companion object {
        const val HIT_CONDITION = 17
        const val BLACKJACK_NUMBER = 21
    }
}
