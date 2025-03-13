package blackjack.model.participant

import blackjack.model.card.Card

abstract class Participant(
    val name: Name,
    initialMoney: Money,
    private val hand: Hand,
) {
    private var money: Money = initialMoney
    val cards: List<Card> get() = hand.cards
    val handState: HandState get() = hand.state

    abstract fun showInitialCards(): List<Card>

    abstract fun isDrawable(): Boolean

    fun recieveMoney(money: Money) {
        this.money = this.money.plus(money)
    }

    fun payMoney(money: Money) {
        this.money = this.money.minus(money)
    }

    fun recieveCards(recieveCards: (Int) -> List<Card>) {
        val count = if (cards.isEmpty()) INITIAL_DRAW_COUNT else DEFAULT_DRAW_COUNT
        addAll(recieveCards(count))
    }

    fun score(): Int = hand.score()

    fun addAll(cards: List<Card>) {
        hand.addAll(cards)
    }

    companion object {
        const val INITIAL_DRAW_COUNT = 2
        const val DEFAULT_DRAW_COUNT = 1
    }
}
