package model.participants

class Players(val players: List<Player>) {
    companion object {
        const val ERROR_EXCEED_PLAYERS = "플레이어의 수는 1 ~ 10 사이여야 합니다."

        fun ofList(
            names: List<String>,
            money: List<Money>,
        ): Players {
            return names.validateLength().zip(money).map { (name, money) ->
                Player(ParticipantState.None(), Wallet(IdCard.fromInput(name), money))
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
