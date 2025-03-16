package util

import model.Cards

class TestCardGenerator {
    fun generateCards(): Cards {
        return Cards(
            listOf(
                TestCards.CLUB_ACE,
                TestCards.SPADE_KING,
                TestCards.HEART_QUEEN,
                TestCards.DIAMOND_JACK,
            ),
        )
    }
}
