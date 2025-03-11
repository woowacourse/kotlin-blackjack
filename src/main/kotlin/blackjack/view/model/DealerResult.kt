package blackjack.view.model

class DealerResult(
    val winCount: Int,
    val loseCount: Int,
    val drawCount: Int,
) {
    override fun toString(): String =
        "${
            if (winCount != 0) "${winCount}승" else ""
        }${
            if (loseCount != 0) " ${loseCount}무" else ""
        }${
            if (drawCount != 0) " ${drawCount}패" else ""
        }"
}
