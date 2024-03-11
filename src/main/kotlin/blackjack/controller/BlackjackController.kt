package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.CardDeckGenerator
import blackjack.model.Dealer
import blackjack.model.Hand
import blackjack.model.ParticipantName
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.State
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun play() {
        val deck = CardDeckGenerator.generate()
        val participants = initializeCardSetting(deck, InputView.readNames())
        OutputView.printInitialStatus(participants)
        playRound(deck, participants)
    }

    private fun initializeCardSetting(
        deck: CardDeck,
        names: List<String>,
    ): Participants {
        val dealer =
            Dealer(state = State.determineInitialGameState(Hand(List(INITIAL_DISTRIBUTE_COUNT) { deck.pick() })))
        val players: List<Player> =
            names.map {
                Player(ParticipantName(it), State.determineInitialGameState(Hand(List(INITIAL_DISTRIBUTE_COUNT) { deck.pick() })))
            }
        return Participants(dealer, players)
    }

    private fun playRound(
        deck: CardDeck,
        participants: Participants,
    ) {
        participants.players.forEach { player ->
            player.playRound(
                deck,
                { playerName -> InputView.askMoreCard(playerName) },
                { participant -> OutputView.printParticipantStatus(participant) },
            )
        }
        participants.dealer.playRound(deck) { participant -> OutputView.printParticipantStatus(participant) }

        OutputView.printStatusAndScore(participants)
        OutputView.printResult(participants.getDealerWinningState(), participants.getPlayerWinningState())
    }

    companion object {
        const val INITIAL_DISTRIBUTE_COUNT = 2
    }
}
