package util

import model.Card

interface Shuffler {
    fun shuffle(cards: List<Card>): List<Card>
}
