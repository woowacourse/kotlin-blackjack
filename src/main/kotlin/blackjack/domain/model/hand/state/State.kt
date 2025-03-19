package blackjack.domain.model.hand.state

import blackjack.domain.model.Card
import blackjack.domain.model.Score

interface State {
    fun cards(): List<Card>

    fun nextState(card: Card): State

    fun stay(): Finished

    fun isFinished(): Boolean

    fun score(): Score
}
