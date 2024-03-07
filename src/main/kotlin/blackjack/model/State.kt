package blackjack.model

sealed interface State {
    fun draw(card: Card): State

    fun stay(): State

    fun hand(): Hand

    companion object {
        fun initializeSetting(
            hand: Hand,
            threshold: Int = THRESHOLD_BLACKJACK,
        ): State {
            return when (hand.calculateSum()) {
                threshold -> Blackjack(hand)
                else -> Hit(hand)
            }
        }

        const val THRESHOLD_BUST = 21
        private const val THRESHOLD_BLACKJACK = 21
    }
}

sealed class Running : State

sealed class Finished : State {
    override fun draw(card: Card): State = this

    override fun stay(): State = this
}

class Hit(private val hand: Hand) : Running() {
    override fun draw(card: Card): State {
        hand.addCard(card)
        return if (hand.calculateSum() > State.THRESHOLD_BUST) {
            Bust(hand)
        } else if (hand.calculateSum() == State.THRESHOLD_BUST) {
            Stay(hand)
        } else {
            Hit(hand)
        }
    }

    override fun stay(): State = Stay(hand)

    override fun hand(): Hand = hand
}

class Stay(private val hand: Hand) : Finished() {
    override fun hand(): Hand = hand
}

class Blackjack(private val hand: Hand) : Finished() {
    override fun hand(): Hand = hand
}

class Bust(private val hand: Hand) : Finished() {
    override fun hand(): Hand = hand
}
