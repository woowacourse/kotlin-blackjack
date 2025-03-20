package blackjack.view

import blackjack.domain.model.hand.UserChoice
import blackjack.domain.model.playing.PlayingParticipant
import blackjack.domain.model.playing.PlayingParticipants

class BaseGameView(
    private val inputView: InputView,
    private val outputView: OutputView,
) : GameView {
    override fun readPlayerBetAmount(playerName: String): Double = inputView.readPlayerBetAmount(playerName)

    override fun readPlayerAction(player: PlayingParticipant): UserChoice = inputView.readPlayerAction(player)

    override fun printInitialDeals(playingParticipants: PlayingParticipants) = outputView.printInitialDeals(playingParticipants)

    override fun printParticipantsStatus(participants: PlayingParticipants) = outputView.printParticipantsStatus(participants)

    override fun printPlayerStatus(player: PlayingParticipant) = outputView.printPlayerStatus(player)

    override fun printDealerHitsState() = outputView.printDealerHitsState()

    override fun printErrorMessage(message: String) = outputView.printErrorMessage(message)
}
