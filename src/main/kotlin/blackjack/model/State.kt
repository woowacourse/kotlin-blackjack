package blackjack.model

sealed interface State {
    fun draw(card: Card): State

    fun stay(): State

    fun hand(): Hand

    companion object {
        const val THRESHOLD_BUST = 21
        private const val THRESHOLD_BLACKJACK = 21

        fun initializeSetting(
            hand: Hand,
            threshold: Int = THRESHOLD_BLACKJACK,
        ): State {
            return when (hand.calculateSum()) {
                threshold -> Blackjack(hand)
                else -> Hit(hand)
            }
        }
    }
}

sealed class Running(private val hand: Hand) : State {
    override fun hand(): Hand = hand
}

sealed class Finished(private val hand: Hand) : State {
    override fun draw(card: Card): State = this
    override fun stay(): State = this
    override fun hand(): Hand = hand
}

class Hit(private val hand: Hand) : Running(hand) {
    override fun draw(card: Card): State {
        hand.addCard(card)
        return when {
            hand.calculateSum() > State.THRESHOLD_BUST -> Bust(hand)
            hand.calculateSum() == State.THRESHOLD_BUST -> Stay(hand)
            else -> Hit(hand)
        }
    }

    override fun stay(): State = Stay(hand)
}

class Stay(hand: Hand) : Finished(hand)
class Blackjack(hand: Hand) : Finished(hand)
class Bust(hand: Hand) : Finished(hand)
