package blackjack.model

import blackjack.model.playing.participants.player.PlayerName

data class Betting(val playerName: PlayerName, val amount: Int)
