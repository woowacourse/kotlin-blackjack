package domain.user

import domain.card.Cards

class Dealer(
    val name: String, override val hand: Cards = Cards(mutableListOf()),
) : User {

    override fun draw(cards: Cards) {
        TODO("Not yet implemented")
    }

    override fun changeStatus() {
        TODO("Not yet implemented")
    }
}
