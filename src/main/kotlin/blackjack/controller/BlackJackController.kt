package blackjack.controller

import blackjack.model.card.CardDeck
import blackjack.model.game.GameResult
import blackjack.model.user.Participant.Dealer
import blackjack.model.user.Participant.Player
import blackjack.view.ProgressInputView
import blackjack.view.ProgressOutputView
import blackjack.view.ResultOutputView
import blackjack.view.SettingInputView

object BlackJackController {
    fun run() {
        val players = SettingInputView.inputPlayers()
        val dealer = Dealer()
        try {
            blackJackGameStart(dealer, players)
            displayBlackJackGameResult(dealer, players)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
        }
    }

    private fun blackJackGameStart(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val cardDeck = CardDeck()
        dealer.initialCardDealing(players, cardDeck)
        ProgressOutputView.outputCardDistribution(dealer, players)
        judgePlayersDraw(players, cardDeck)
        dealer.judgeDrawOrNot(cardDeck) { ProgressOutputView.outputDealerDraw(dealer) }
    }

    private fun judgePlayersDraw(
        players: List<Player>,
        cardDeck: CardDeck,
    ) {
        players.forEach { player ->
            player.judgeDrawOrNot(
                cardDeck,
                readDecision = { ProgressInputView.inputDrawDecision(player.participantInformation.name).judgeDecision() },
                output = { ProgressOutputView.outputParticipantCard(player) },
            )
        }
    }

    private fun displayBlackJackGameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        ResultOutputView.outputGameScores(dealer, players)
        ResultOutputView.outputGameResult(GameResult(dealer, players))
    }
}
