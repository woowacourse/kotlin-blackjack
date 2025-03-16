package blackjack.domain.service

import blackjack.domain.model.BetStatus
import blackjack.domain.model.Proceed
import blackjack.domain.model.ProceedStatus
import blackjack.domain.model.participant.Dealer

class BlackjackResult(private val dealer: Dealer) {
    fun calculatePlayersProceed(betStatus: List<BetStatus>): List<ProceedStatus> {
        return betStatus.map { betInfo ->
            val result = betInfo.player.compareScores(dealer)
            ProceedStatus(betInfo.player, Proceed(betInfo.betAmount * result.earningRate))
        }
    }

    fun calculateDealerProceed(playerProceedStatus: List<ProceedStatus>): ProceedStatus {
        val playersProceedSum = playerProceedStatus.sumOf { it.proceed.amount }
        val dealerProceed = Proceed(playersProceedSum * -1)
        return ProceedStatus(dealer, dealerProceed)
    }
}
