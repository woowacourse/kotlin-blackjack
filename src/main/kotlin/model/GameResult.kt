package model

class GameResult private constructor(val playersFinalResult: Map<Name, Int>) {
    fun getDealerProfitResult(): Int = playersFinalResult.values.sum() * -1

    companion object {
        private const val ZERO = 0
        private fun calculate(bet: Bet, result: FinalResult) = when (result) {
            FinalResult.WIN -> bet.amount
            FinalResult.BLACKJACK_WIN -> (bet.amount * 0.5).toInt()
            FinalResult.LOSE -> (bet.amount * -1)
            FinalResult.PUSH -> (ZERO)
        }

        fun of(dealer: Dealer, betInfos: BetInfos): GameResult =
            GameResult(
                buildMap {
                    betInfos.infos.forEach {
                        put(it.key.name, calculate(it.value, it.key.judge(dealer)))
                    }
                }
            )
    }
}
