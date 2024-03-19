package blackjack.view

import blackjack.model.state.BlackJack
import blackjack.model.state.Bust
import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Hand
import blackjack.model.state.Hit
import blackjack.model.Player
import blackjack.model.PlayerGroup
import blackjack.model.Profit
import blackjack.model.state.Running
import blackjack.model.state.Running.Companion.INITIAL_CARD_COUNTS
import blackjack.model.state.Stay

object OutputView {
    fun printGameSetting(
        dealerCard: Card,
        playerGroup: PlayerGroup,
    ) {
        println(
            "\n딜러와 ${
                playerGroup.players.joinToString(", ") {
                    it.name.name
                }
            }에게 ${INITIAL_CARD_COUNTS}장의 카드를 나누었습니다.",
        )
        showDealerInitCard(dealerCard)
        showPlayersInitCards(playerGroup)
        println()
    }

    private fun showDealerInitCard(dealerCard: Card) {
        println("딜러: $dealerCard")
    }

    private fun showPlayersInitCards(playerGroup: PlayerGroup) {
        playerGroup.players.forEach { player ->
            showPlayerCards(player)
        }
    }

    fun showPlayerCards(player: Player) {
        println(convertPlayerCardsToString(player))
        showStateOfPlayer(player)
    }

    private fun showStateOfPlayer(player: Player) {
        when (player.state) {
            is Bust -> println("${player.name}의 카드가 ${Hand.BLACKJACK_NUMBER}을 초과하여 Bust되었습니다.")
            is Stay -> println("${player.name}이(가) Stay에 성공하였습니다.")
            is BlackJack -> println("${player.name}이(가) BlackJack을 달성했습니다.")
            is Hit -> return
            is Running -> return
        }
    }

    private fun convertPlayerCardsToString(player: Player) =
        "${player.name}: ${player.state.hand.cards.joinToString(", ")}"


    private fun convertDealerCardsToString(dealerHand: Hand) =
        "딜러: ${dealerHand.cards.joinToString(", ")}"

    fun printDealerDrawCard(drawCount: Int) {
        repeat(drawCount) { println("\n딜러의 카드가 ${Dealer.THRESHOLD}이하 이므로, 1장의 카드를 더 받습니다.") }
    }

    fun printEveryCards(
        dealerHand: Hand,
        playerGroup: PlayerGroup,
    ) {
        println()
        showDealerCardsResult(dealerHand)
        showPlayersCardsResult(playerGroup)
    }

    private fun showDealerCardsResult(dealerHand: Hand) {
        println("${convertDealerCardsToString(dealerHand)} - 결과: ${dealerHand.calculate()}")
    }

    private fun showPlayersCardsResult(playerGroup: PlayerGroup) {
        playerGroup.players.forEach { player ->
            println("${convertPlayerCardsToString(player)} - 결과: ${player.state.hand.calculate()}")
        }
    }

    fun printMatchResult(
        dealerResult: GameResult,
        playerGroup: PlayerGroup,
    ) {
        println("\n[ 최종 승패 ]")
        println("딜러: ${dealerResult.display()}")
        playerGroup.players.forEach { player ->
            println("${player.name}: ${player.gameResult.display()}")
        }
    }

    fun printFinalProfit(
        dealerProfit: Profit,
        playerGroup: PlayerGroup,
    ) {
        println("\n[ 최종 수익 현황 ]")
        println("딜러: ${dealerProfit.amount.toInt()}")
        playerGroup.players.forEach { player ->
            println("${player.name}: ${player.state.profit.amount.toInt()}")
        }
    }

    private fun GameResult.display(): String {
        var answer = ""
        if (win != GameResult.DEFAULT_RESULT_VALUE) answer += "${win}승 "
        if (push != GameResult.DEFAULT_RESULT_VALUE) answer += "${push}무 "
        if (defeat != GameResult.DEFAULT_RESULT_VALUE) answer += "${defeat}패 "
        return answer
    }
}
