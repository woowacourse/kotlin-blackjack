package blackjack.model.game

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.game.UserCommand.HIT
import blackjack.model.game.UserCommand.STAY
import blackjack.model.game.UserCommand.UNKNOWN
import blackjack.model.participant.Dealer
import blackjack.model.participant.Hand
import blackjack.model.participant.Name
import blackjack.model.participant.Player
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

    fun progressPlayerDrawUntilFinished(
        player: Player,
        draw: (Int) -> List<Card>,
        getCommand: () -> UserCommand,
        onCardReceived: (List<Card>) -> Unit,
    ) {
        while (true) {
            when (getCommand()) {
                HIT -> {
                    player.recieveCards(draw)
                    onCardReceived(player.cards)
                    if (!player.isDrawable()) return
                }
                STAY -> break
                UNKNOWN -> throw IllegalArgumentException("[ERROR] 올바르지 않은 입력입니다.")
            }
        }
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
