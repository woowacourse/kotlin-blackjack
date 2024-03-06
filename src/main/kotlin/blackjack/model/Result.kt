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
}
