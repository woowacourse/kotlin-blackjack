import blackjack.model.card.Hand
import blackjack.model.game.State

class Player(val name: String, val hand: Hand) {
    var state: State = State.Running.Hit
}
