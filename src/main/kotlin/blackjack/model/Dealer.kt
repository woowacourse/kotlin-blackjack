package blackjack.model

class Dealer(
    cardDeck: CardDeck,
) : Participant(cardDeck) {
    fun drawUntilFinished(): Int {
        var count = 0

        while (hand.score() <= 16 && !hand.isBust()) {
            draw()
            count++
        }

        return count
    }

    fun result(playerScores: List<Int>): Map<WinningResult, Int> {
        val result = WinningResult.entries.associateWith { 0 }.toMutableMap()

        playerScores.forEach { playerScore ->
            val winningResult = WinningResult.from(hand.score(), playerScore)
            result[winningResult] = result.getOrDefault(winningResult, 0) + 1
        }

        return result.toMap()
    }
}
