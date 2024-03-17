package blackjack.model

sealed class ParticipantState(private val hand: Hand, private val info: ParticipantInfo) {
    abstract val isBlackjack: Boolean
    abstract val isBust: Boolean
    abstract val isFinished: Boolean

    fun getHand() = hand

    fun getInfo() = info

    fun getCardsSum() = hand.calculateCardsSum()

    abstract fun calculateGameOutcome(opponent: ParticipantState): GameOutcome

    abstract fun drawCard(card: Card): ParticipantState

    companion object {
        private const val BLACKJACK_CARD_SIZE = 2
        private const val THRESHOLD_BLACKJACK = 21
        const val THRESHOLD_BUST = 21

        fun calculateInitialParticipantState(
            hand: Hand,
            info: ParticipantInfo,
        ): ParticipantState {
            return when {
                hand.calculateCardsSum() == THRESHOLD_BLACKJACK && hand.getCards().size == BLACKJACK_CARD_SIZE ->
                    Blackjack(hand, info)

                hand.calculateCardsSum() > THRESHOLD_BUST -> Bust(hand, info)
                else -> Normal(hand, info)
            }
        }
    }
}

class Blackjack(hand: Hand, info: ParticipantInfo) : ParticipantState(hand, info) {
    override val isBlackjack = true
    override val isBust = false
    override val isFinished = true

    override fun calculateGameOutcome(opponent: ParticipantState): GameOutcome {
        if (opponent.isBlackjack) return GameOutcome.Tie
        return GameOutcome.WinWhenBlackjack
    }

    override fun drawCard(card: Card): ParticipantState {
        return this
    }
}

class Bust(hand: Hand, info: ParticipantInfo) : ParticipantState(hand, info) {
    override val isBlackjack = false
    override val isBust = true
    override val isFinished = true

    override fun calculateGameOutcome(opponent: ParticipantState): GameOutcome {
        if (this.getInfo().isDealer() && opponent.isBust) return GameOutcome.Win
        return GameOutcome.Lose
    }

    override fun drawCard(card: Card): ParticipantState {
        return this
    }
}

class Normal(hand: Hand, info: ParticipantInfo) : ParticipantState(hand, info) {
    override val isBlackjack = false
    override val isBust = false
    override val isFinished = false

    override fun calculateGameOutcome(opponent: ParticipantState): GameOutcome {
        return when {
            opponent.isBust || getCardsSum() > opponent.getCardsSum() -> GameOutcome.Win
            opponent.isBlackjack || getCardsSum() < opponent.getCardsSum() -> GameOutcome.Lose
            else -> GameOutcome.Tie
        }
    }

    override fun drawCard(card: Card): ParticipantState {
        getHand().addCard(card)
        return when {
            getHand().calculateCardsSum() > THRESHOLD_BUST -> Bust(getHand(), getInfo())
            else -> Normal(getHand(), getInfo())
        }
    }
}
