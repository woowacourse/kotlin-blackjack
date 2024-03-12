package blackjack.model.winning

import blackjack.model.playing.participants.player.PlayerName

data class PlayerWinning(val result: Map<PlayerName, WinningResultStatus>)
