package blackjack.domain.service

import blackjack.domain.model.BetStatus
import blackjack.domain.model.Proceed
import blackjack.domain.model.card.PlayingCard
import blackjack.domain.model.participant.Participants
import blackjack.domain.model.participant.Player
import blackjack.domain.model.participant.PlayerGroup

class Blackjack(
    private val deck: PlayingCard,
    val playerGroup: PlayerGroup,
) {
    fun initGame() {
        playerGroup.participants.forEach { player ->
            player.receiveCard(deck.spreadCard(INITIAL_CARD_COUNT))
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

    fun hitAction(participant: Participants) {
        participant.receiveCard(deck.spreadCard(ONE_CARD))
    }

    fun getGameResult(betStatus: List<BetStatus>): Map<Player, Proceed> {
        return playerGroup.players.associateWith { player ->
            val result = player.compareScores(playerGroup.dealer)
            val playerBetInfo = betStatus.find { it.player == player } ?: throw IllegalArgumentException()
            Proceed(playerBetInfo.betAmount.calculateProceed(result))
        }
    }

    companion object {
        private const val INITIAL_CARD_COUNT: Int = 2
        private const val ONE_CARD: Int = 1
    }
}
