package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.CardDeckGenerator
import blackjack.model.Dealer
import blackjack.model.DealerInfo
import blackjack.model.ParticipantBetAmount
import blackjack.model.ParticipantName
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.PlayerInfo
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
        val dealer = setupDealer(deck)
        val players = setupPlayers(deck, names)
        return Participants(dealer, players)
    }

    private fun setupDealer(deck: CardDeck): Dealer {
        return Dealer(DealerInfo(), deck.createStartHand())
    }

    private fun setupPlayers(
        deck: CardDeck,
        names: List<String>,
    ): List<Player> {
        val participantNames = names.map { name -> ParticipantName(name) }
        val participantBetAmounts =
            participantNames.map {
                ParticipantBetAmount(InputView.readBetAmount(it))
            }

        val players: List<Player> =
            participantNames.zip(participantBetAmounts).map { (name, betAmount) ->
                Player(PlayerInfo(name, betAmount), deck.createStartHand())
            }
        return players
    }
}
