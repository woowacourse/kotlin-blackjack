package blackjack

interface Participant {
    fun getCardHandState(isHit: Boolean): CardHandState
}
