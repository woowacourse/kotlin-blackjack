package blackjack.domain.participants.user

import blackjack.domain.card.Card
import blackjack.domain.state.FirstTurn
import blackjack.domain.state.State

class Dealer(
    name: Name = Name("딜러"),
    state: State = FirstTurn(),
) : User(name, state) {
    private val isDrawable: Boolean
        get() = state.score.isDealerAvailable

    fun calculateProfit(guests: List<Guest>): Int = guests.sumOf { it.calculateProfit(this) } * -1

    override fun draw(card: Card): Result<Unit> = runCatching {
        when {
            isDrawable -> super.draw(card)
            else -> throw IllegalStateException()
        }
    }
}
