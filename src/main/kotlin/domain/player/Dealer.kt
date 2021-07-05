package domain.player

const val MINIMUM_SCORE_OF_DEALER = 16

class Dealer(name: String) : Player(name) {

    val shouldDraw: Boolean
        get() = cards.score() <= MINIMUM_SCORE_OF_DEALER

    fun compete(player: Player) {
        if (isWin(player)) {
            takeMoney(player, player.bettingMoney)
        }

        if (isLose(player)) {
            giveMoney(player, decidePrize(player))
        }
    }

    private fun takeMoney(gambler: Player, money: Money) {
        gambler.lose(money)
        this.earn(money)
    }

    private fun giveMoney(gambler: Player, money: Money) {
        gambler.earn(money)
        this.lose(money)
    }

    private fun decidePrize(player: Player): Money {
        return if (player.isBlackJack()) {
            player.bettingMoney.asBlackJackPrize()
        } else {
            player.bettingMoney
        }
    }
}
