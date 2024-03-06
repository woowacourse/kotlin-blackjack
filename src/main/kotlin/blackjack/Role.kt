package blackjack

abstract class Role(private val cardHand: CardHand) {
    abstract fun getState(hitCondition: Boolean): CardHandState
}
