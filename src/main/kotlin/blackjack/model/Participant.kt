package blackjack.model

sealed class Participant(private val info: ParticipantInfo, private val hand: Hand) {
    fun getName() = info.name

    fun getCards() = hand.getCards()

    fun getCardsSum() = hand.calculateCardsSum()

    protected fun drawCard(card: Card) {
        hand.addCard(card)
    }

    fun getParticipantState(): ParticipantState {
        return when {
            hand.calculateCardsSum() == THRESHOLD_BLACKJACK && hand.getCards().size == BLACKJACK_CARD_SIZE -> Blackjack
            hand.calculateCardsSum() > THRESHOLD_BUST -> Bust
            else -> Normal
        }
    }

    protected fun isGameFinished() = getParticipantState().isFinished

    fun calculateGameStateAgainst(opponent: Participant): GameState = getParticipantState().calculateGameState(self = this, opponent)

    companion object {
        private const val BLACKJACK_CARD_SIZE = 2
        private const val THRESHOLD_BLACKJACK = 21
        const val THRESHOLD_BUST = 21
    }
}

class Dealer(info: DealerInfo, hand: Hand) :
    Participant(info, hand) {
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

    companion object {
        private const val THRESHOLD_DRAW_FOR_DEALER = 16
    }
}

class Player(private val info: PlayerInfo, hand: Hand) : Participant(info, hand) {
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

    fun calculateProfitAgainst(opponent: Participant): Double {
        return info.betAmount.getAmount() * (calculateGameStateAgainst(opponent).payoutMultiplier)
    }
}
