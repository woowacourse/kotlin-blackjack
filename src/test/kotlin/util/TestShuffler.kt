package util

import model.Card

class TestShuffler : Shuffler {
    override fun shuffle(cards: List<Card>) = cards
}
