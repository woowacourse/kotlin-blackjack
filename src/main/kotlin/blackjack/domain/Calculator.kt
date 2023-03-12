package blackjack.domain

class Calculator {
    private fun getDividend(player: Player, consequence: Consequence): Int {
        return when (consequence) {
            Consequence.WIN -> if (player.isBlackjack()) player.bettingMoney * MULTI_BLACKJACK else player.bettingMoney * MULTI_WIN
            Consequence.LOSE -> player.bettingMoney * MULTI_LOSE
            Consequence.DRAW -> player.bettingMoney * MULTI_DRAW
        }
    }

    fun calculateDividend(playerResultsBy: Map<Player, Consequence>): Map<Player, Int> {
        return playerResultsBy.asSequence().associate {
            it.key to it.key.getProfit(getDividend(it.key, it.value))
        }
    }

    companion object {
        private const val MULTI_BLACKJACK = 2.5
        private const val MULTI_WIN = 2.0
        private const val MULTI_LOSE = 0.0
        private const val MULTI_DRAW = 1.0
    }
}
