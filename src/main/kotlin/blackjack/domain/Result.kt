package blackjack.domain

enum class Result(val word: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    companion object {

        fun reverse(result: Result) = when (result) {
            WIN -> LOSE
            LOSE -> WIN
            else -> DRAW
        }
    }
}
