package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.RandomShuffler
import blackjack.view.AskView
import blackjack.view.ResultView
import blackjack.view.model.PlayerConfig

class BlackjackController(
    val askView: AskView = AskView(),
    val resultView: ResultView = ResultView(),
) {
    lateinit var players: List<Player>
    lateinit var dealer: Dealer
    lateinit var blackjack: Blackjack

    fun initGame() {
        val playerConfigs: List<PlayerConfig> = askView.readPlayers()
        players = playerConfigs.toPlayers
        dealer = Dealer(players, RandomShuffler)
        blackjack = Blackjack(dealer)
    }

    fun dealCards() {
        blackjack.dealCards()
        resultView.showDealing(
            players.names,
            dealer.cards.prettyString,
            players.playersCards,
        )
    }

    fun playPlayerTurn() {
        blackjack.startPlayerTurn(
            onStart = { player ->
                resultView.showPlayerCard(
                    player.name,
                    player.cards.prettyString,
                    player.score.value,
                )
            },
            wantToHit = { player ->
                askView.askWantToHit(player.name)
            },
            afterHit = { player ->
                resultView.showPlayerCard(
                    player.name,
                    player.cards.prettyString,
                    player.score.value,
                )
            },
        )
    }

    fun playDealerTurn() {
        blackjack.startDealerTurn {
            resultView.showDealerHit()
        }
    }

    fun showParticipantsSummary() {
        resultView.showParticipantsSummary(
            dealer.summary,
            players.summaries,
        )
    }

    fun setResult() {
        blackjack.setResult()
        resultView.showResult(
            dealer.result,
            players.results,
        )
    }
}
