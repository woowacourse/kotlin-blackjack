package entity

enum class CardNumber(val value: Int) {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    KING(10),
    QUEEN(10),
    JACK(10),

    /*ACE({ sumOfCards ->
        if (sumOfCards + 11 > GameRule.WINNING_NUMBER) 1
        else 11
    }),
    TWO({ _ -> 2 }),
    THREE({ _ -> 3 }),
    FOUR({ _ -> 4 }),
    FIVE({ _ -> 5 }),
    SIX({ _ -> 6 }),
    SEVEN({ _ -> 7 }),
    EIGHT({ _ -> 8 }),
    NINE({ _ -> 9 }),
    TEN({ _ -> 10 }),
    KING({ _ -> 10 }),
    QUEEN({ _ -> 10 }),
    JACK({ _ -> 10 }),*/
}
