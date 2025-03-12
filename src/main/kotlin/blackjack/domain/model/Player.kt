package blackjack.domain.model

class Player(name: String) : Participant(name) {
    var bet: Int = 0

    constructor(name: String, vararg cards: Card) : this(name) {
        accept(cards.toList())
    }

    override fun canHit(): Boolean {
        return !isBusted()
    }

    override fun showHand(): List<Card> {
        return hand.show()
    }

    fun placeBet(input: (Player) -> Int): Int {
        val amount = input(this)
        require(amount >= 0) { ERROR_MESSAGE_BET_NOT_POSITIVE }
        bet = amount
        return amount
    }

    fun processHits(
        deck: Deck,
        readAction: (Player) -> Action,
        printStatus: (Player) -> Unit,
    ) {
        if (!canHit()) return
        if (readAction(this) == Action.STAND) {
            if (showHand().size == INITIAL_DRAW_COUNT) printStatus(this)
            return
        }
        accept(deck.draw())
        printStatus(this)
        processHits(deck, readAction, printStatus)
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

    companion object {
        private const val ERROR_MESSAGE_BET_NOT_POSITIVE = "베팅 금액은 음수일 수 없습니다."
    }
}
