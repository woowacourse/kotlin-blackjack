package domain

class Player(nameAndBet: PlayerInfo) : Participant(nameAndBet.name) {
    val betMoney = nameAndBet.betMoney
    override fun isPossibleDrawCard(): Boolean = !isBust()
}
