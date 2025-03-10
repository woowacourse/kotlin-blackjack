package model

interface Shuffler {
    fun <T> shuffle(list: List<T>): List<T>
}

class RandomShuffler : Shuffler {
    override fun <T> shuffle(list: List<T>) = list.shuffled()
}

class TestShuffler : Shuffler {
    override fun <T> shuffle(list: List<T>) = list
}
