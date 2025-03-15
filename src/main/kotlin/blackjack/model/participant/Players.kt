package blackjack.model.participant

import blackjack.model.betting.BettingTable
import blackjack.model.card.Card

class Players private constructor(
    val value: List<Player>,
) {
    init {
        require(value.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) {
            "[ERROR] 플레이어 수는 1명 이상부터 7명 이하만 가능합니다. 입력값: ${value.size}"
        }
        require(value.map { player -> player.name }.distinct().size == value.size) {
            "[ERROR] 플레이어 이름은 중복될 수 없습니다. 입력값: ${value.joinToString()}"
        }
    }

    fun receiveMoney(
        name: Name,
        money: Money,
    ) {
        value.find { it.name == name }?.receiveMoney(money) ?: return
    }

    fun getMoney(getBettingMoney: (Name) -> Money): BettingTable {
        val bettingTable = BettingTable()

        value.forEach { player ->
            val bettingMoney = getBettingMoney(player.name)
            require(bettingMoney > Money.ZERO) {
                ("[ERROR] 베팅 금액은 0원보다 높아야 합니다.")
            }
            bettingTable.add(player.name, player.payMoney(bettingMoney))
        }

        return bettingTable
    }

    fun draw(
        newCards: (Int) -> List<Card>,
        choice: (Name) -> UserCommand,
        onCardReceived: (Name, List<Card>) -> Unit,
    ) {
        value.forEach { player ->
            player.progressDraw(newCards, choice, onCardReceived)
        }
    }

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 7

        fun from(names: List<Name>): Players = Players(names.map { name -> Player.create(name) })

        fun from(vararg names: String): Players = Players(names.map { Name(it) }.map { name -> Player.create(name) })
    }
}
