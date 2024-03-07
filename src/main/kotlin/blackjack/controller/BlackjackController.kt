package blackjack.controller

import blackjack.model.*
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun play() {
        val deck = CardDeckGenerator.generate()
        val participants = initialSetting(deck, InputView.readNames())
        val blackjackGame = BlackjackGame(deck, participants)
        OutputView.printInitialStatus(participants)

        blackjackGame.playRound(
            { playerName -> InputView.askMoreCard(playerName) },
            { participant -> OutputView.printParticipantStatus(participant) },
        )

        OutputView.printStatusAndScore(blackjackGame.participants)
        OutputView.printResult(blackjackGame.calculateResult())
    }

    private fun initialSetting(
        deck: CardDeck,
        names: List<String>,
    ): Participants {
        val dealer = Dealer(state = State.initializeSetting(Hand(deck.initialDistribute())))
        val players: List<Player> =
            names.map { Player(ParticipantName(it), State.initializeSetting(Hand(deck.initialDistribute()))) }

        return Participants(dealer, players)
    }
}
