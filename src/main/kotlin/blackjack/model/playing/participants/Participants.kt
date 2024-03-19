package blackjack.model.playing.participants

import blackjack.model.card.CardDeck
import blackjack.model.playing.participants.player.Players
import blackjack.model.winning.DealerWinning
import blackjack.model.winning.FinalWinning
import blackjack.model.winning.WinningResultStatus

data class Participants(val dealer: Dealer, val players: Players) {
    fun addInitialCards(cardDeck: CardDeck) {
        dealer.addInitialCards(cardDeck)
        players.addInitialCards(cardDeck)
    }

    fun getFinalWinning(): FinalWinning {
        val dealerResult = judgeDealerWinning()
        val dealerWinning = DealerWinning(getVictoryCount(dealerResult), getDefeatCount(dealerResult), getPushCount(dealerResult))
        val playersWinning = players.judgePlayersWinning(dealer)

        return FinalWinning(dealerWinning, playersWinning)
    }

    private fun getVictoryCount(dealerResult: Map<WinningResultStatus, Int>): Int =
        dealerResult.getOrDefault(
            WinningResultStatus.VICTORY,
            0,
        )

    private fun getDefeatCount(dealerResult: Map<WinningResultStatus, Int>): Int = dealerResult.getOrDefault(WinningResultStatus.DEFEAT, 0)

    private fun getPushCount(dealerResult: Map<WinningResultStatus, Int>): Int = dealerResult.getOrDefault(WinningResultStatus.PUSH, 0)

    private fun judgeDealerWinning(): Map<WinningResultStatus, Int> {
        val dealerWinning = mutableMapOf<WinningResultStatus, Int>().withDefault { 0 }

        players.players.forEach {
            val winningStatus = dealer.match(it)
            dealerWinning[winningStatus] = dealerWinning.getValue(winningStatus) + 1
        }
        return dealerWinning
    }
}
