package blackjack.model

class Dealer(val hand: Hand) {
    fun canHit(): Boolean {
        if (hand.isBust()) return false
        return hand.sumOptimized() < HIT_CONDITION
    }

    fun hit(card: Card) {
        hand.add(card)
    }

    fun hitUntilBust(
        deck: Deck,
        view: () -> Unit,
    ) {
        while (canHit()) {
            hit(deck.pull())
            view()
        }
    }

    fun createScoreBoard(players: List<Player>): ScoreBoard {
        val results: List<PlayerResult> =
            players.map { player ->
                PlayerResult(player.name, player.comparePoints(this))
            }
        return ScoreBoard.from(results)
    }

    companion object {
        const val HIT_CONDITION = 17
        const val BLACKJACK_NUMBER = 21
    }
}
