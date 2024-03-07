package blackjack.model

abstract class Role(open val name: String, open val cardHand: CardHand) {
    abstract fun getState(): CardHandState

    fun addInitialCards() {
        repeat(2) {
            cardHand.addNewCard()
        }
    }

    fun runPhase() {
        if (getState() == CardHandState.HIT) cardHand.addNewCard()
    }
}
