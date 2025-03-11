package blackjack.view

import blackjack.domain.GameResultStatus

internal fun GameResultStatus.toDisplayName(): String {
    return when (this) {
        GameResultStatus.PLAYER_WIN -> "승"
        GameResultStatus.PLAYER_LOSE -> "패"
        GameResultStatus.DRAW -> "무"
    }
}
