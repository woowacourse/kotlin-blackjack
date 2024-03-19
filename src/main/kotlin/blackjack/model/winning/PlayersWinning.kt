package blackjack.model.winning

import blackjack.model.playing.participants.player.PlayerName

data class PlayersWinning(val result: Map<PlayerName, WinningResultStatus>)
