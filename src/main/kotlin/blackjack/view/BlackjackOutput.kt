package blackjack.view

import blackjack.model.state.ResultType
import blackjack.model.user.Dealer
import blackjack.model.user.Player

interface BlackjackOutput {
    fun printInitialHandOutCardMessage(players: List<Player>)

    fun printAllPlayerHands(
        dealer: Dealer,
        players: List<Player>,
    )

    fun printPlayerHands(player: Player)

    fun printDealerHandStatus(dealerCondition: Boolean)

    fun printFinalHandStatus(
        dealer: Dealer,
        players: List<Player>,
    )

    fun printFinalResult(
        playersGameResult: Map<Player, ResultType>,
        dealerGameResult: Map<ResultType, Int>,
    )
}
