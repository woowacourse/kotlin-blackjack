package blackjack.model.state

import blackjack.base.BaseHolder
import blackjack.model.GameResult

interface CompareImpl {
    fun decideWinner(opponent: BaseHolder): GameResult
}
