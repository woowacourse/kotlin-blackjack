package blackjack.view.blackjackView

import blackjack.domain.gameResult.GameResultStatus

internal fun GameResultStatus.toDisplayName(): String {
    return when (this) {
        GameResultStatus.PLAYER_WIN -> "승"
        GameResultStatus.PLAYER_LOSE -> "패"
        GameResultStatus.DRAW -> "무"
    }
}
