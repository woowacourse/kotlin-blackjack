package blackjack.domain

class Calculator {
    fun calculateDividend(player: Player, consequence: Consequence): Int {
        return when (consequence) {
            Consequence.WIN -> player.bettingMoney.multipleTwo()
            Consequence.LOSE -> player.bettingMoney.multipleZero()
            Consequence.DRAW -> player.bettingMoney.multipleOne()
        }
    }
}
