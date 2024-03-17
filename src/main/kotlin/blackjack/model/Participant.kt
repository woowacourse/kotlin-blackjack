package blackjack.model

sealed class Participant(private var state: ParticipantState) {
    fun getState() = state

    fun getName() = state.getInfo().name

    fun getCards() = state.getHand().getCards()

    fun getCardsSum() = state.getHand().calculateCardsSum()

    fun calculateGameOutcomeAgainst(opponent: Participant): GameOutcome = state.calculateGameOutcome(opponent.getState())

    protected fun drawCard(card: Card) {
        state = state.drawCard(card)
    }

    protected fun isGameFinished() = state.isFinished

    abstract fun calculateProfitAgainst(opponent: Participant): Double
}

class Dealer(state: ParticipantState) :
    Participant(state) {
    private fun shouldDrawCardForDealer(threshold: Int = THRESHOLD_DRAW_FOR_DEALER): Boolean = getCardsSum() <= threshold

    fun playRound(
        updateDealerCards: (Dealer) -> Unit,
        deck: CardDeck,
    ) {
        while (!isGameFinished() && shouldDrawCardForDealer()) {
            drawCard(deck.pick())
            updateDealerCards(this)
        }
    }

    override fun calculateProfitAgainst(opponent: Participant) =
        opponent.getState().getInfo().getBetAmount() * (calculateGameOutcomeAgainst(opponent).payoutMultiplier)

    companion object {
        private const val THRESHOLD_DRAW_FOR_DEALER = 16
    }
}

class Player(state: ParticipantState) : Participant(state) {
    fun playRound(
        requestMoreCards: (ParticipantName) -> Boolean,
        updatePlayerCards: (Player) -> Unit,
        deck: CardDeck,
    ) {
        while (!isGameFinished() && requestMoreCards(this.getName())) {
            drawCard(deck.pick())
            updatePlayerCards(this)
        }
    }

    override fun calculateProfitAgainst(opponent: Participant) =
        getState().getInfo().getBetAmount() * (calculateGameOutcomeAgainst(opponent).payoutMultiplier)
}
