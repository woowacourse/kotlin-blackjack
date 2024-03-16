package blackjack.model.playing.participants

import blackjack.model.card.CardDeck
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.playing.participants.player.Players
import blackjack.model.winning.DealerWinning
import blackjack.model.winning.PlayerWinning
import blackjack.model.winning.Winning
import blackjack.model.winning.WinningResultStatus

data class Participants(val dealer: Dealer, val players: Players) {
    fun addInitialCards(cardDeck: CardDeck) {
        dealer.addInitialCards(cardDeck)
        players.players.forEach {
            it.addInitialCards(cardDeck)
        }
    }

    fun getFinalResult(): Winning {
        val dealerWinning = DealerWinning(getVictoryCount(), getDefeatCount(), getPushCount())
        val playersWinning = judgePlayersResult()

        return Winning(dealerWinning, playersWinning)
    }

    private fun getVictoryCount(): Int = getPlayersFinalResult().getOrDefault(WinningResultStatus.VICTORY, 0)

    private fun getDefeatCount(): Int = getPlayersFinalResult().getOrDefault(WinningResultStatus.DEFEAT, 0)

    private fun getPushCount(): Int = getPlayersFinalResult().getOrDefault(WinningResultStatus.PUSH, 0)

    private fun getPlayersFinalResult(): Map<WinningResultStatus, Int> {
        val playersWinning: PlayerWinning = judgePlayersResult()

        return playersWinning.result.values.groupingBy { it.reverse() }
            .eachCount()
    }

    private fun getPlayersScore(): Map<PlayerName, Int> = players.players.associate { it.name to it.cardHand.calculateScore() }

    private fun judgePlayersResult(): PlayerWinning =
        PlayerWinning(
            getPlayersScore().mapValues { (_, playerSum) ->
                judgeGameResult(playerSum)
            },
        )

    private fun judgeGameResult(playerSum: Int): WinningResultStatus {
        val dealerSum = dealer.cardHand.calculateScore()

        return when {
            playerSum > BLACK_JACK_SCORE -> WinningResultStatus.DEFEAT
            dealerSum > BLACK_JACK_SCORE -> WinningResultStatus.VICTORY
            dealerSum > playerSum -> WinningResultStatus.DEFEAT
            dealerSum == playerSum -> WinningResultStatus.PUSH
            else -> WinningResultStatus.VICTORY
        }
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
