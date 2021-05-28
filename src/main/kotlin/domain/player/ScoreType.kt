package domain.player

private const val BLACK_JACK_POINT = 21
private const val NUMBER_OF_INITIAL_CARDS = 2

enum class ScoreType {
    UNDER_BLACK_JACK,
    BLACK_JACK,
    OVER_BLACK_JACK;

    companion object {
        fun of(point :Int, numberOfCards: Int): ScoreType {
            if(point == BLACK_JACK_POINT && numberOfCards <= NUMBER_OF_INITIAL_CARDS){
                return BLACK_JACK
            }

            if(point <= BLACK_JACK_POINT){
                return UNDER_BLACK_JACK
            }

            return OVER_BLACK_JACK
        }
    }
}