package blackjack.model

abstract class Role(open val name: PlayerName, open val cardHand: CardHand) {
    abstract fun getState(): CardHandState

    fun addInitialCards() {
        repeat(INITIAL_CARDS_COUNT) {
            cardHand.addNewCard()
        }
    }

    fun runPhase() {
        cardHand.addNewCard()
    }

    companion object {
        private const val INITIAL_CARDS_COUNT = 2
    }
}
