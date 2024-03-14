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

        playRound(deck, participants)

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

    private fun playRound(
        deck: CardDeck,
        participants: Participants,
    ) {
        playRoundForPlayers(deck, participants.getPlayers())
        playRoundForDealer(deck, participants.getDealer())
    }

    private fun playRoundForPlayers(
        deck: CardDeck,
        players: List<Player>,
    ) {
        players.forEach { player ->
            player.playRound(
                { playerName -> InputView.askMoreCard(playerName) },
                { playerAfterRound -> OutputView.printPlayerStatus(playerAfterRound) },
                deck,
            )
        }
    }

    private fun playRoundForDealer(
        deck: CardDeck,
        dealer: Dealer,
    ) {
        dealer.playRound(
            { dealerAfterRound -> OutputView.printDealerStatus(dealerAfterRound) },
            deck,
        )
    }
}
