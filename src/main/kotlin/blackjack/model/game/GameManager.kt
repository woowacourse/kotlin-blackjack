package blackjack.model.game

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.participant.Dealer
import blackjack.model.participant.Hand
import blackjack.model.participant.Name
import blackjack.model.participant.Players
import blackjack.model.rule.ScoreCalculator
import blackjack.model.rule.WinningResult

class GameManager {
    fun prepareDealer(
        dealerName: Name,
        cardDeck: CardDeck,
        scoreCalculator: ScoreCalculator,
    ): Dealer {
        val dealer = Dealer(dealerName, Hand(scoreCalculator))
        dealer.recieveCards(cardDeck::draw)

        return dealer
    }

    fun preparePlayers(
        playerNames: List<Name>,
        cardDeck: CardDeck,
        scoreCalculator: ScoreCalculator,
    ): Players {
        val players = Players.from(playerNames, scoreCalculator)
        players.value.forEach { player -> player.recieveCards(cardDeck::draw) }

        return players
    }

    fun progressDealerDraw(
        dealer: Dealer,
        draw: (Int) -> List<Card>,
    ) {
        while (dealer.isDrawable()) {
            dealer.recieveCards(draw)
        }
    }

    fun getResult(resultManager: ResultManager): GameResult {
        val dealerResult: Map<WinningResult, ResultCount> = resultManager.dealerResult()
        val playerResults: Map<Name, WinningResult> = resultManager.playerResults()

        return GameResult(dealerResult, playerResults)
    }
}
