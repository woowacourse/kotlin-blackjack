package blackjack.model

import blackjack.model.WinningResult.LOSE
import blackjack.model.WinningResult.PUSH
import blackjack.model.WinningResult.WIN

object CalculateResult {
    private fun getResult(oneSelf: Participant, other: Participant): WinningResult {
        val oneSelfBlackjack = oneSelf.getHandSize() == 2 && oneSelf.getScore() == 21
        val otherBlackjack = other.getHandSize() == 2 && other.getScore() == 21

        return when {
            oneSelfBlackjack && otherBlackjack -> PUSH
            oneSelfBlackjack -> WIN
            otherBlackjack -> LOSE
            oneSelf.getScore() > other.getScore() -> WIN
            oneSelf.getScore() < other.getScore() -> LOSE
            else -> PUSH
        }
    }

    fun getUserResult(dealer: Dealer, player: Player): WinningResult = getResult(player, dealer)

    fun getDealerResult(dealer: Dealer, player: Player): WinningResult = getResult(dealer, player)
}
