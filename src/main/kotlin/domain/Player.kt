package domain

class Player(nameAndBet: NameAndBet) : Participant(nameAndBet.name) {
    val betMoney = nameAndBet.betMoney
    override fun isPossibleDrawCard(): Boolean = !isBust()
}
