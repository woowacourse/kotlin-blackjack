package blackjack.exception

enum class ErrorCode(val status: Int, val reason: String) {
    NO_CARDS_ERROR(1000, "카드 덱에 카드가 없습니다. 카드를 다시 섞겠습니다."),
    INVALID_NAME_LENGTH_ERROR(1001, "%s의 길이는 1 에서 20 사이가 아닙니다"),
    INVALID_PLAYERS_COUNT_ERROR(1002, "플레이어의 수는 1 ~ 8명 사이여야 합니다"),
}
