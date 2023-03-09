package entity

enum class CardNumber(val numberStrategy: (sumOfCardNumbers: Int) -> Int) {
    ACE({ sumOfCards ->
        if (sumOfCards + 11 > Player.WINNING_NUMBER) {
            1
        } else {
            11
        }
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
    JACK({ _ -> 10 }),
}
