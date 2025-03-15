package blackjack.model

import blackjack.model.ResultCalculator.BLACKJACK_PROFIT_MULTIPLIER
import blackjack.model.ResultCalculator.LOSS_PROFIT_MULTIPLIER
import blackjack.model.ResultCalculator.TIE_PROFIT_MULTIPLIER
import blackjack.model.amount.BetAmount
import blackjack.model.amount.WinningMoney
import blackjack.model.participant.Dealer
import blackjack.model.participant.Participant
import blackjack.model.participant.Player

class GameManager(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    private var deck = Deck()

    fun dealInitialCards() {
        deck = Deck()
        dealInitialCardWithCount()
    }

    fun dealInitialCardWithCount() {
        repeat(INITIAL_HAND_OUT_CARD_COUNT) {
            dealer.addCard(deck.draw())
            players.forEach { player -> player.addCard(deck.draw()) }
        }
    }

    fun calculateResultMap(): Map<Player, ResultType> {
        val playersStatus =
            players.associateBy(
                { player -> player },
                { player -> ResultCalculator.judgeScore(dealer, player) },
            )
        return playersStatus
    }

    fun gameResult(result: Map<Player, ResultType>): List<Profit> {
        val playerProfits =
            result.map { (player, resultType) ->
                Profit(player, calculateProfit(resultType, player))
            }

        val dealerProfit = calculateDealerProfit(playerProfits)
        return playerProfits + listOf(dealerProfit)
    }

    fun drawCard(person: Participant) {
        person.addCard(deck.draw())
    }

    fun calculateProfit(
        resultType: ResultType,
        player: Player,
    ) = when (resultType) {
        ResultType.BLACKJACK -> WinningMoney(player.betAmount.toDouble() * BLACKJACK_PROFIT_MULTIPLIER)
        ResultType.WIN -> WinningMoney(player.betAmount.toDouble())
        ResultType.TIE -> WinningMoney(player.betAmount.toDouble() * TIE_PROFIT_MULTIPLIER)
        ResultType.LOSS -> WinningMoney(player.betAmount.toDouble() * LOSS_PROFIT_MULTIPLIER)
    }

    fun calculateDealerProfit(profitResults: List<Profit>): Profit {
        val totalPlayerProfit = profitResults.sumOf { it.winningMoney.amount }
        return Profit(dealer, WinningMoney(totalPlayerProfit * DEALER_PROFIT_MULTIPLIER))
    }

    private fun BetAmount.toDouble(): Double {
        return this.amount.toDouble()
    }

    companion object {
        const val INITIAL_HAND_OUT_CARD_COUNT = 2
        const val DEALER_PROFIT_MULTIPLIER = -1.0
    }
}
