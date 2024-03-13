package blackjack.model

sealed class Participant(private val name: ParticipantName, private val hand: Hand) {
    fun getName() = name

    fun getCards() = hand.getCards()

    fun getCardsSum() = hand.calculateCardsSum()

    protected fun drawCard(card: Card) {
        hand.addCard(card)
    }

    fun getState(): State {
        return when {
            hand.calculateCardsSum() == THRESHOLD_BLACKJACK && hand.getCards().size == BLACKJACK_CARD_SIZE -> Blackjack()
            hand.calculateCardsSum() > THRESHOLD_BUST -> Bust()
            else -> Normal()
        }
    }

    protected fun isGameFinished() = getState().isFinished

    fun calculateWinningStateAgainst(opponent: Participant) = getState().calculateWinningState(this, opponent)

    companion object {
        private const val BLACKJACK_CARD_SIZE = 2
        private const val THRESHOLD_BLACKJACK = 21
        const val THRESHOLD_BUST = 21
    }
}

class Dealer(name: ParticipantName = ParticipantName(ParticipantName.DEALER_NAME), hand: Hand) :
    Participant(name, hand) {
    private fun shouldDrawCardForDealer(threshold: Int = THRESHOLD_DRAW_FOR_DEALER): Boolean = getCardsSum() <= threshold

    fun playRound(
        updateDealerInfo: (Dealer) -> Unit,
        deck: CardDeck,
    ) {
        while (!isGameFinished() && shouldDrawCardForDealer()) {
            drawCard(deck.pick())
            updateDealerInfo(this)
        }
    }

    companion object {
        private const val THRESHOLD_DRAW_FOR_DEALER = 16
    }
}

class Player(name: ParticipantName, hand: Hand) : Participant(name, hand) {
    fun playRound(
        requestMoreCards: (ParticipantName) -> Boolean,
        updatePlayerInfo: (Player) -> Unit,
        deck: CardDeck,
    ) {
        while (!isGameFinished() && requestMoreCards(this.getName())) {
            drawCard(deck.pick())
            updatePlayerInfo(this)
        }
    }
}
