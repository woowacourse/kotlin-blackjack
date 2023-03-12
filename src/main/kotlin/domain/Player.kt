package domain

class Player(playerInfo: PlayerInfo) : Participant(playerInfo.name) {
    val betMoney: BetMoney = playerInfo.betMoney
    override fun isPossibleDrawCard(): Boolean = cardsState.isFinished.not()
}
