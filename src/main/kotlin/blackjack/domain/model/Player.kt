package blackjack.domain.model

class Player(name: String) : Participant(name) {
    constructor(name: String, vararg cards: Card) : this(name) {
        accept(cards.toList())
    }

    override fun canHit(): Boolean {
        return !isBusted()
    }

    override fun showHand(): List<Card> {
        return hand.show()
    }

    fun compareAgainst(dealer: Dealer): Result {
        if (isBusted()) return Result.LOSE
        if (dealer.isBusted()) return Result.WIN

        val point: Int = computePoint()
        val dealerPoint: Int = dealer.computePoint()
        return when {
            point > dealerPoint -> Result.WIN
            point < dealerPoint -> Result.LOSE
            else -> Result.DRAW
        }
    }
}
