package blackjack.model

fun createSinglePlayerGameInfo(): GameInfo =
    GameInfo(
        name = "케이엠",
        cards =
            setOf(
                Card.of(Shape.CLOVER, CardValue.SIX, 0),
                Card.of(Shape.HEART, CardValue.K, 6),
            ),
    )

fun createMultiplePlayersGameInfo(): List<GameInfo> =
    listOf(
        GameInfo(
            "케이엠",
            cards =
                setOf(
                    Card.of(Shape.DIAMOND, CardValue.SEVEN, 0),
                    Card.of(Shape.DIAMOND, CardValue.K, 7),
                ),
        ),
        GameInfo(
            "해음",
            cards =
                setOf(
                    Card.of(Shape.DIAMOND, CardValue.SIX, 0),
                    Card.of(Shape.SPADE, CardValue.K, 6),
                ),
        ),
        GameInfo(
            "차람",
            cards =
                setOf(
                    Card.of(Shape.DIAMOND, CardValue.FIVE, 0),
                    Card.of(Shape.CLOVER, CardValue.K, 5),
                ),
        ),
    )

fun createBurstDealerGameInfo(): GameInfo =
    GameInfo(
        "딜러",
        cards =
            setOf(
                Card.of(Shape.CLOVER, CardValue.SIX, 0),
                Card.of(Shape.HEART, CardValue.K, 6),
                Card.of(Shape.DIAMOND, CardValue.K, 16),
            ),
    )

fun createHitDealerGameInfo(): GameInfo =
    GameInfo(
        "딜러",
        cards =
            setOf(
                Card.of(Shape.CLOVER, CardValue.SIX, 0),
                Card.of(Shape.HEART, CardValue.K, 6),
            ),
    )

fun createStandDealerGameInfo(): GameInfo =
    GameInfo(
        "딜러",
        cards =
            setOf(
                Card.of(Shape.CLOVER, CardValue.SEVEN, 0),
                Card.of(Shape.HEART, CardValue.K, 7),
            ),
    )

fun createDealerOnBlackjackInfo(): GameInfo =
    GameInfo(
        "딜러",
        cards =
            setOf(
                Card.of(Shape.CLOVER, CardValue.SEVEN, 0),
                Card.of(Shape.HEART, CardValue.SEVEN, 7),
                Card.of(Shape.SPADE, CardValue.SEVEN, 14),
            ),
    )

fun createMultiPlayersResultInfo(): List<GameInfo> =
    listOf(
        GameInfo(
            "케이엠",
            moneyAmount = Money(1000),
            cards =
                setOf(
                    Card.of(Shape.DIAMOND, CardValue.J, 0),
                    Card.of(Shape.DIAMOND, CardValue.K, 10),
                ),
        ),
        GameInfo(
            "해음",
            moneyAmount = Money(2000),
            cards =
                setOf(
                    Card.of(Shape.DIAMOND, CardValue.SIX, 0),
                    Card.of(Shape.SPADE, CardValue.K, 6),
                    Card.of(Shape.HEART, CardValue.K, 16),
                ),
        ),
        GameInfo(
            "차람",
            moneyAmount = Money(3000),
            cards =
                setOf(
                    Card.of(Shape.DIAMOND, CardValue.FIVE, 0),
                    Card.of(Shape.CLOVER, CardValue.K, 5),
                ),
        ),
    )
