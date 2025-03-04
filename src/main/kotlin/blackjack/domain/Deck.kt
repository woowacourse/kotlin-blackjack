package blackjack.domain


        fun pick(): Card = cards.removeLast()

            Suit.entries
                .flatMap { suit -> Rank.entries.map { rank -> Card(rank, suit) } }
    }
}
