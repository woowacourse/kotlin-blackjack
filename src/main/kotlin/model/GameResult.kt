package model

class GameResult private constructor(val playersFinalResult: Map<Name, Int>) {
    fun getDealerProfitResult(): Int = playersFinalResult.values.sum() * -1

    companion object {
        fun of(dealer: Dealer, betInfos: BetInfos): GameResult =
            GameResult(
                buildMap {
                    betInfos.infos.forEach {
                        put(it.key.name, (it.value.amount * it.key.judge(dealer).rate).toInt())
                    }
                }
            )
    }
}
