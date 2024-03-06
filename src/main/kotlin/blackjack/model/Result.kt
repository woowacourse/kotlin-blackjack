package blackjack.model

data class Result(
    val win: Int = 0,
    val defeat: Int = 0,
    val push: Int = 0,
) {
    override fun toString(): String {
        var answer = ""
        if (win != 0) answer += "${win}승 "
        if (push != 0) answer += "${push}무 "
        if (defeat != 0) answer += "${defeat}패 "
        return answer
    }

    fun deepCopy(
        newWin: Int = 0,
        newDefeat: Int = 0,
        newPush: Int = 0,
    ): Result {
        return copy(win = win + newWin, defeat = defeat + newDefeat, push = push + newPush)
    }
}
