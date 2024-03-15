package model.participants

class Players(val players: List<Player>) {
    fun betMoney(readBetMoney: (Player) -> Money) {
        players.forEach { player ->
            val betMoney = readBetMoney(player)
            player.wallet = player.wallet.copy(money = betMoney)
        }
    }

    companion object {
        const val ERROR_EXCEED_PLAYERS = "플레이어의 수는 1 ~ 10 사이여야 합니다."

        fun ofList(names: List<String>): Players {
            return names.validateLength().map {
                Player(ParticipantState.None(), Wallet(ParticipantName.fromInput(it), Money()))
            }.run {
                Players(this)
            }
        }

        private fun List<String>.validateLength(): List<String> {
            require(this.size in 1..10) { ERROR_EXCEED_PLAYERS }
            return this
        }
    }
}
