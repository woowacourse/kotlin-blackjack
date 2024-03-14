package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.CardDeckGenerator
import blackjack.model.Dealer
import blackjack.model.ParticipantName
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun play() {
        val deck = CardDeckGenerator.generate()
        val participants = setupParticipants(deck, InputView.readNames())
        OutputView.printParticipantsStatus(participants)

        participants.playRound(deck, participants)

        OutputView.printParticipantsStatusAndScore(participants)
        OutputView.printGameResult(participants.calculateResult())
    }

    private fun setupParticipants(
        deck: CardDeck,
        names: List<String>,
    ): Participants {
        val dealer = Dealer(hand = deck.createStartHand())
        val players: List<Player> =
            names.map { Player(ParticipantName(it), deck.createStartHand()) }

        return Participants(dealer, players)
    }
}
