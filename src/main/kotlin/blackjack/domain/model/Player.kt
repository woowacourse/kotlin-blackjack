package blackjack.domain.model

class Player(name: String, cards: List<Card>) : Participant(name, cards) {
    private lateinit var bet: Bet

    constructor(name: String, vararg cards: Card) : this(name, cards.toList())

    constructor(name: String, bet: Int, vararg cards: Card) : this(name, cards.toList()) {
        this.bet = Bet(bet)
    }

    override fun canHit(): Boolean {
        return !isBusted()
    }

    override fun showHand(): List<Card> {
        return hand.show()
    }

    fun placeBet(input: (Player) -> Bet) {
        bet = input(this)
    }

    fun processHits(
        deck: Deck,
        readAction: (Player) -> Action,
        printStatus: (Player) -> Unit,
    ) {
        if (!canHit()) return
        if (readAction(this) == Action.STAND) {
            if (showHand().size == Hand.STARTING_HAND_SIZE) printStatus(this)
            return
        }
        accept(deck.draw())
        printStatus(this)
        processHits(deck, readAction, printStatus)
    }

    fun computeProfitAgainst(dealer: Dealer): Int {
        val result: Result = compareAgainst(dealer)
        return Math.round(bet.amount * result.profitRate).toInt()
    }

    fun compareAgainst(dealer: Dealer): Result {
        if (isBusted()) return Result.LOSE
        if (dealer.isBusted()) return Result.WIN

        if (isBlackJack() && dealer.isBlackJack()) return Result.PUSH
        if (isBlackJack()) return Result.BLACKJACK

        val point: Int = computePoint()
        val dealerPoint: Int = dealer.computePoint()
        return when {
            point > dealerPoint -> Result.WIN
            point < dealerPoint -> Result.LOSE
            else -> Result.PUSH
        }
    }
}
