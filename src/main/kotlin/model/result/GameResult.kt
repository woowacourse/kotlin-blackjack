package model.result

import model.participants.BetInfo
import model.participants.Dealer
import model.participants.Name

class GameResult private constructor(val playersFinalResult: Map<Name, Int>) {
    fun getDealerProfitResult(): Int = playersFinalResult.values.sum() * -1

    companion object {
        fun of(dealer: Dealer, betInfos: BetInfo): GameResult =
            GameResult(
                buildMap {
                    betInfos.info.forEach {
                        put(it.key.name, (it.value.amount * it.key.judge(dealer).rate).toInt())
                    }
                }
            )
    }
}
