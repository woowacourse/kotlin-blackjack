package blackjack.domain.participants.user

import blackjack.domain.card.Card
import blackjack.domain.state.FirstTurn
import blackjack.domain.state.Hit
import blackjack.domain.state.State
import blackjack.domain.state.Stay

class Dealer(
    name: Name = Name("딜러"),
    state: State = FirstTurn(),
) : User(name, state) {
    fun calculateProfit(guests: List<Guest>): Int = guests.sumOf { it.calculateProfit(this) } * -1

    override fun draw(card: Card) {
        super.draw(card)
        if (state is Hit && isDrawable.not()) state = Stay(state.cards)
    }

    private val isDrawable: Boolean
        get() = state.score.value < DEALER_MIN_NUMBER

    companion object {
        private const val DEALER_MIN_NUMBER = 17
    }
}
