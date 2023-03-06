package domain.person

import domain.card.HandOfCards
import domain.card.strategy.GetMinSum
import domain.constant.BLACK_JACK

class Player(
    override val name: String,
    override val handOfCards: HandOfCards,
) : Person() {
    override fun canReceiveMoreCard() = getTotalCardNumber(GetMinSum()) <= BLACK_JACK
}
