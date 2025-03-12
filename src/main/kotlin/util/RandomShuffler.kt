package util

import model.Card

class RandomShuffler : Shuffler {
    override fun shuffle(cards: List<Card>) = cards.shuffled()
}
