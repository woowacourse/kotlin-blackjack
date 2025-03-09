package blackjack.domain.model

class Player(name: String) : Participant(name) {
    constructor(name: String, cards: List<Card>) : this(name) {
        accept(cards)
    }

    fun compareAgainst(dealer: Dealer): Verdict {
        if (isBusted()) return Verdict.LOSE
        if (dealer.isBusted()) return Verdict.WIN

        val score: Int = computeScore()
        val dealerScore: Int = dealer.computeScore()
        return when {
            score > dealerScore -> Verdict.WIN
            score < dealerScore -> Verdict.LOSE
            else -> Verdict.DRAW
        }
    }
}
