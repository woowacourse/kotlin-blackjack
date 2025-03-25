package blackjack.view

interface GameViewListener {
    fun onPlayerHit(name: String): Boolean
}
