package blackjack

class Dealer : Participant() {
    override val hitThreshold: Int
        get() = DEALER_HIT_THRESHOLD

    fun getCard(deck: Deck) {
        while (canHit()) {
            addCard(deck.draw())
        }
    }

    fun getPlayerResult(player: Player): GameResultStatus {
        if (player.isBust()) return GameResultStatus.PLAYER_LOSE
        if (isBust()) return GameResultStatus.PLAYER_WIN
        return when {
            totalSum > player.totalSum -> GameResultStatus.PLAYER_LOSE
            player.totalSum > totalSum -> GameResultStatus.PLAYER_WIN
            player.totalSum == totalSum -> GameResultStatus.DRAW
            else -> throw IllegalArgumentException()
        }
    }

    fun haveAdditionalCard(): Boolean {
        return cards.size > 2
    }

    companion object {
        const val DEALER_HIT_THRESHOLD = 17
    }
}
