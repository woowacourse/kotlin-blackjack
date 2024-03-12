package blackjack.controller

import blackjack.model.card.CardDeck
import blackjack.model.card.generator.RandomCardGenerator
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import blackjack.model.playing.participants.Dealer
import blackjack.model.playing.participants.Participants
import blackjack.model.playing.participants.player.Player
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.playing.participants.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJack(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val cardDeck = CardDeck.cardDeck
    private val randomCardGenerator = RandomCardGenerator(cardDeck)

    fun gameStart() {
        val dealer = Dealer(CardHand())
        val players = initPlayers()

        val participants = Participants(dealer, players)

        dealInitialCards(participants)
        runPlayersPhase(players)

        runDealerPhase(dealer)
        showFinalWinning(participants)
    }

    private fun initPlayers(): Players =
        Players(
            inputView.readPlayersName()
                .map {
                    Player(PlayerName(it), CardHand())
                },
        )

    private fun dealInitialCards(participants: Participants) {
        outputView.printInitialSetting(participants)

        participants.addInitialCards(randomCardGenerator)
        outputView.printInitialCardHands(participants)
    }

    private fun runPlayersPhase(players: Players) {
        players.players.forEach {
            runPlayerPhase(it)
        }
    }

    private fun runPlayerPhase(it: Player) {
        while (it.getState() == CardHandState.STAY && inputView.readIsHit(it)) {
            it.runPhase(randomCardGenerator)
            outputView.printPlayerCardHand(it)
        }
    }

    private fun runDealerPhase(dealer: Dealer) {
        if (dealer.getState() == CardHandState.HIT) {
            outputView.printDealerHit()
            dealer.runPhase(randomCardGenerator)
        }
    }

    private fun showFinalWinning(participants: Participants) {
        outputView.printGameResult(participants)

        val playerWinning = participants.dealer.judgePlayerWinningResult(participants.getPlayerResult())
        val dealerWinning = participants.dealer.judgeDealerWinningResult(playerWinning)

        outputView.printFinalDealerResult(dealerWinning)
        outputView.printFinalPlayersResult(playerWinning)
    }
}
