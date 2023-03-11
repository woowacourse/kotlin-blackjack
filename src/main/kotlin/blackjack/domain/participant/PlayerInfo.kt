package blackjack.domain.participant

data class PlayerInfo(val name: String, val money: Int, val askDraw: (String) -> Boolean = { true })
