package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.money.Money
import blackjack.domain.state.CardState
import blackjack.domain.state.StartState

class Player(
    name: String,
    cardState: CardState = StartState(),
    private val money: Money,
    val needToDraw: () -> Boolean = { true }
) : Participant(name, cardState) {

    override fun getFirstOpenCards(): List<Card> = getCards()

    override fun stay(): Participant = Player(name, cardState.stay(), money, needToDraw)

    override fun draw(card: Card): Participant = Player(name, cardState.draw(card), money, needToDraw)
}
