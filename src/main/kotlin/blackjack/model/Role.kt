package blackjack.model

abstract class Role(open val name: PlayerName, open val cardHand: CardHand) {
    abstract fun getState(): CardHandState

    fun addInitialCards(cardGenerator: CardGenerator) {
        repeat(INITIAL_CARDS_COUNT) {
            cardHand.addNewCard(cardGenerator)
        }
    }

    fun runPhase(cardGenerator: CardGenerator) {
        cardHand.addNewCard(cardGenerator)
    }

    companion object {
        private const val INITIAL_CARDS_COUNT = 2
    }
}
