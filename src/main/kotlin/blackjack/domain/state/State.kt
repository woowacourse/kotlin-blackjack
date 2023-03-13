package blackjack.domain.state

import blackjack.domain.card.Card

interface State {
    val isFinished: Boolean

    fun draw(card: Card): State

    fun stay(): State

    fun getFirstCard(): Card

    fun getAllCards(): List<Card>

    fun getTotalScore(): Int
}
