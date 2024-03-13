package blackjack.controller

import blackjack.model.BetAmount
import blackjack.model.CardDeck
import blackjack.model.CardDeckGenerator
import blackjack.model.Dealer
import blackjack.model.Hand
import blackjack.model.ParticipantName
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        val deck = CardDeckGenerator.generate()
        val playerNames = InputView.readNames().map { ParticipantName(it) }
        val participants = initializeGameSetting(deck, playerNames)
        OutputView.printInitialStatus(participants)

        playRound(deck, participants)
        OutputView.printStatusAndScore(participants)

        val playerProfits = participants.getPlayerProfits()
        val dealerProfits = participants.getDealerProfits(playerProfits)
        OutputView.printProfit(dealerProfits, playerProfits)
    }

    private fun initializeGameSetting(
        deck: CardDeck,
        playerNames: List<ParticipantName>,
    ): Participants {
        val players =
            playerNames.map { playerName ->
                Player(playerName, createInitialHand(deck), BetAmount(InputView.readBetAmount(playerName)))
            }
        val dealer = Dealer(hand = createInitialHand(deck))
        return Participants(dealer, players)
    }

    private fun createInitialHand(deck: CardDeck): Hand = Hand(List(INITIAL_DISTRIBUTE_COUNT) { deck.pick() })

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
    }

    companion object {
        const val INITIAL_DISTRIBUTE_COUNT = 2
    }
}
