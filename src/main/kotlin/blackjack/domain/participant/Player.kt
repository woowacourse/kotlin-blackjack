package blackjack.domain.participant

import blackjack.domain.BetAmount
import blackjack.domain.HandStatus
import blackjack.domain.ResultStatus

class Player(val name: String, private val betAmount: BetAmount) : Participant() {
    override val hitThreshold: Int
        get() = PLAYER_HIT_THRESHOLD

    fun setPlayerStatus(dealer: Dealer): ResultStatus {
        val playerStatus = getStatus()
        val dealerStatus = dealer.getStatus()

        val result =
            when {
                // 블랙잭 판별
                playerStatus == HandStatus.BLACKJACK && dealerStatus != HandStatus.BLACKJACK -> ResultStatus.BLACKJACK_WIN
                playerStatus == HandStatus.BLACKJACK && dealerStatus == HandStatus.BLACKJACK -> ResultStatus.DRAW
                dealerStatus == HandStatus.BLACKJACK -> ResultStatus.PLAYER_LOSE

                // 버스트 판별
                playerStatus == HandStatus.BUST -> ResultStatus.PLAYER_LOSE
                dealerStatus == HandStatus.BUST -> ResultStatus.PLAYER_WIN

                // 점수 비교
                getTotalSum() > dealer.getTotalSum() -> ResultStatus.PLAYER_WIN
                getTotalSum() < dealer.getTotalSum() -> ResultStatus.PLAYER_LOSE
                else -> ResultStatus.DRAW
            }
        betAmount.update(result)
        return result
    }

    fun getBetAmount() = betAmount.getAmount()

    companion object {
        const val PLAYER_HIT_THRESHOLD = 21
    }
}
