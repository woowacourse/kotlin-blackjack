package blackjack

abstract class Role(open val cardHand: CardHand) {
    abstract fun getState(hitCondition: Boolean): CardHandState

    fun addInitialCards() {
        repeat(2) {
            cardHand.addNewCard()
        }
    }
}
