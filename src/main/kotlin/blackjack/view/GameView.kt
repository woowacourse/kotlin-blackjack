package blackjack.view

import blackjack.domain.model.hand.UserChoice
import blackjack.domain.model.playing.PlayingParticipant
import blackjack.domain.model.playing.PlayingParticipants

interface GameView {
    fun readPlayerBetAmount(playerName: String): Double

    fun readPlayerAction(player: PlayingParticipant): UserChoice

    fun printInitialDeals(playingParticipants: PlayingParticipants)

    fun printParticipantsStatus(participants: PlayingParticipants)

    fun printPlayerStatus(player: PlayingParticipant)

    fun printDealerHitsState()

    fun printErrorMessage(message: String)
}
