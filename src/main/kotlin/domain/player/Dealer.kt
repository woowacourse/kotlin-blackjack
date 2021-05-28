package domain.player

const val MINIMUM_SCORE_OF_DEALER = 16

class Dealer(name: String) : Player(name) {

    fun compare(player: Player) {
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
        if (player.isBlackJack()) {
            return player.bettingMoney.asBlackJackPrize()
        }
        return player.bettingMoney
    }

    fun shouldDraw(): Boolean {
        return cards.score() <= MINIMUM_SCORE_OF_DEALER
    }
}
