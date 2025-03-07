package blackjack.domain

enum class GameResultStatus {
    PLAYER_WIN,
    PLAYER_LOSE,
    DRAW,
}

fun GameResultStatus.toDisplayName(): String {
    return when (this) {
        GameResultStatus.PLAYER_WIN -> "승"
        GameResultStatus.PLAYER_LOSE -> "패"
        GameResultStatus.DRAW -> "무"
    }
}
