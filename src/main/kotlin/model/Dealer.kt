package model

import model.Cards.Companion.DEALER_STANDARD_HIT_POINT

class Dealer(cards: Cards, name: Name = Name(DEALER)) : Participant(cards, name) {
    override fun getFirstOpenCards(): Cards = Cards(setOf(cards.firstCard()))

    override fun isPossibleDrawCard(): Boolean = cards.sum() <= DEALER_STANDARD_HIT_POINT

    override fun isHit(needToDraw: (String) -> Boolean): Boolean = isPossibleDrawCard()

    companion object {
        const val DEALER = "딜러"
    }
}
