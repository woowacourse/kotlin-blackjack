package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.RandomShuffler
import blackjack.domain.update.participant.NewDealer
import blackjack.domain.update.participant.NewPlayer
import blackjack.view.AskView
import blackjack.view.ResultView
import blackjack.view.model.PlayerSummary

class Blackjack(
    private val askView: AskView = AskView(),
    private val resultView: ResultView = ResultView(),
) {
    fun play() {
        val deck = Deck(RandomShuffler)
        val players: List<NewPlayer> = askView.readPlayers().toNewPlayers(deck)
        val dealer = NewDealer(deck)
        resultView.showDealing(
            playersName = players.names,
            dealerCards = dealer.cardsPrettyString,
            playersCards = players.cardsPrettyStrings,
        )
        startPlayersTurn(players)
        startDealerTurn(dealer)
        resultView.showParticipantsSummary(
            dealer.summary,
            players.summaries,
        )
        resultView.showProfit(players.toResults(dealer.state))
    }

    private fun startPlayersTurn(players: List<NewPlayer>) {
        players.forEach { player ->
            resultView.showPlayerCard(
                PlayerSummary(
                    player.name,
                    player.cards.prettyString,
                    player.score.value,
                ),
            )

            while (!player.isFinished) {
                val wantToHit: Boolean = askView.askWantToHit(player.name)
                if (wantToHit) {
                    player.hit()
                    resultView.showPlayerCard(
                        PlayerSummary(
                            player.name,
                            player.cards.prettyString,
                            player.score.value,
                        ),
                    )
                } else {
                    player.stay()
                }
            }
        }
    }

    private fun startDealerTurn(dealer: NewDealer) {
        dealer.hit()
        while (!dealer.isFinished) {
            if (dealer.score < 17) {
                resultView.showDealerHit()
                dealer.hit()
            } else {
                dealer.stay()
            }
        }
    }
}
