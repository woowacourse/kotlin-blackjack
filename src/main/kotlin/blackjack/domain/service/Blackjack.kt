package blackjack.domain.service

import blackjack.domain.model.BetStatus
import blackjack.domain.model.ProceedStatus
import blackjack.domain.model.card.PlayingCard
import blackjack.domain.model.participant.Participants
import blackjack.domain.model.participant.PlayerGroup

class Blackjack(
    private val deck: PlayingCard,
    val playerGroup: PlayerGroup,
) {
    fun initGame() {
        playerGroup.participants.forEach { player ->
            hitAction(player, INITIAL_CARD_COUNT)
        }
    }

    fun canHit(participant: Participants): Boolean {
        return participant.canHit()
    }

    fun getParticipantCardSize(participant: Participants): Int {
        return participant.cardDeck.size
    }

    fun drawUntilDealerStands(): Int {
        var count: Int = 0
        while (canHit(playerGroup.dealer)) {
            hitAction(playerGroup.dealer)
            count++
        }
        return count
    }

    fun hitAction(
        participant: Participants,
        count: Int = ONE_CARD,
    ) {
        participant.receiveCard(deck.spreadCard(count))
    }

    fun endGame(betStatus: List<BetStatus>): List<ProceedStatus> {
        val resultCalculator = BlackjackResult(playerGroup.dealer)
        val playerProceed = resultCalculator.calculatePlayersProceed(betStatus)
        val dealerProceed = resultCalculator.calculateDealerProceed(playerProceed)
        return playerProceed + dealerProceed
    }

    companion object {
        private const val INITIAL_CARD_COUNT: Int = 2
        private const val ONE_CARD: Int = 1
    }
}
