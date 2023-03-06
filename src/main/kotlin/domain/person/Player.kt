package domain.person

import constant.BlackJackRule.BLACK_JACK
import domain.card.HandOfCards
import domain.card.strategy.GetMinSum

class Player(
    override val name: String,
    override val handOfCards: HandOfCards,
) : Person() {
    override fun canReceiveMoreCard() = getTotalCardNumber(GetMinSum()) <= BLACK_JACK
}
