package domain.player

class Dealer(name: String) : Player(name) {

    fun compare(player: Player) {
        if (isWin(player)) {
            takeMoneyFromPlayer(player)
        }

        if (isLose(player)) {
            givePlayerMoney(player)
        }
    }

    private fun takeMoneyFromPlayer(player: Player) {
        this.takeMoney(player, player.bettingMoney)
    }

    private fun givePlayerMoney(player: Player) {
        if (player.isBlackJack()) {
            this.giveMoney(player, (player.bettingMoney * 1.5).toInt())
        }

        if (!player.isBlackJack()) {
            this.giveMoney(player, player.bettingMoney)
        }
    }
}
