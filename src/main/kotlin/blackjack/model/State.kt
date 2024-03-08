package blackjack.model

sealed class State(val hand: Hand) {
    abstract fun draw(card: Card): State

    abstract fun stay(): State

    companion object {
        fun setupState(
            hand: Hand,
            threshold: Int = THRESHOLD_BLACKJACK,
        ): State {
            return when (hand.calculateSum()) {
                threshold -> Blackjack(hand)
                else -> Hit(hand)
            }
        }

        const val THRESHOLD_BUST = 21
        const val THRESHOLD_HIT_FOR_DEALER = 16
        private const val THRESHOLD_BLACKJACK = 21
    }
}

sealed class Running(hand: Hand) : State(hand)

sealed class Finished(hand: Hand) : State(hand) {
    override fun draw(card: Card): State {
        return this
    }

    override fun stay(): State {
        return this
    }
}

class Hit(hand: Hand) : Running(hand) {
    override fun draw(card: Card): State {
        hand.addCard(card)
        return when {
            hand.calculateSum() > THRESHOLD_BUST -> Bust(hand)
            hand.calculateSum() == THRESHOLD_BUST -> Stay(hand)
            else -> Hit(hand)
        }
    }

    override fun stay(): State = Stay(hand)
}

class Stay(hand: Hand) : Finished(hand)

class Blackjack(hand: Hand) : Finished(hand)

class Bust(hand: Hand) : Finished(hand)
