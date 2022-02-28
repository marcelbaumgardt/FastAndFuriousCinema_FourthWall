package com.marcel.baumgardt.model.mapper

import com.google.common.collect.ImmutableList
import com.marcel.baumgardt.model.connector.OpenMovieDatabaseRating
import com.marcel.baumgardt.model.connector.OpenMovieDatabaseResponse
import com.marcel.baumgardt.model.dto.common.MovieDetail
import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class OpenMovieDatabaseResponseMapper {

    fun mapToMovieDetail(openMovieDatabaseResponse: OpenMovieDatabaseResponse): MovieDetail {
        val (boxOfficeCurrencyCode, boxOfficeCurrencyValue) = mapToBoxOfficePairCurrencyValue(openMovieDatabaseResponse.boxOffice)

        return MovieDetail(
            openMovieDatabaseResponse.title,
            openMovieDatabaseResponse.writer,
            mapToListOfActors(openMovieDatabaseResponse.actors),
            mapToAverageRatingScore(openMovieDatabaseResponse.ratings),
            openMovieDatabaseResponse.metaScore.toInt(),
            boxOfficeCurrencyCode,
            boxOfficeCurrencyValue
        )
    }

    private fun mapToBoxOfficePairCurrencyValue(boxOffice: String): Pair<String, Long> {
        val currencySymbol = boxOffice.substring(0, 1)
        val currencyCode = Currency.getAvailableCurrencies().stream()
            .filter { c: Currency -> c.symbol == currencySymbol }
            .map { obj: Currency -> obj.currencyCode }
            .findFirst()
            .orElse(UNKNOWN_CURRENCY_CODE)
        val value = boxOffice.substring(1).replace(",", "").toLong()
        return Pair(currencyCode, value)
    }

    private fun mapToAverageRatingScore(ratings: List<OpenMovieDatabaseRating>): Double {
        return ratings.stream()
            .mapToDouble { x: OpenMovieDatabaseRating -> unifyDoubleValue(x.value) }
            .average()
            .orElse(0.0)
    }

    private fun unifyDoubleValue(value: String): Double {
        return when (DoubleType.getDoubleTypeBySymbol(value)) {
            DoubleType.PERCENT ->
                getPercentValue(value)
            DoubleType.SLASH -> {
                getSlashValue(value)
            }
            else -> 0.0
        }
    }

    private fun getPercentValue(value: String): Double {
        return value.replace(DoubleType.PERCENT.symbol, "").toDouble() / 100.00
    }

    private fun getSlashValue(value: String): Double {
        val values = Arrays.stream(value.split(DoubleType.SLASH.symbol.toRegex()).toTypedArray())
            .map { s: String -> s.toDouble() }
            .collect(Collectors.toList())
        return values[0] / values[1]
    }

    private fun mapToListOfActors(actors: String): Set<String> {
        return Arrays.stream(actors.split(ACTORS_SPLIT_OPERATOR.toRegex()).toTypedArray())
            .map { obj: String -> obj.trim { it <= ' ' } }
            .collect(Collectors.toSet())
    }

    @Getter
    @AllArgsConstructor
    internal enum class DoubleType(
        val symbol: String
    ) {
        PERCENT("%"),
        SLASH("/"),
        OTHER("");

        companion object {
            fun getDoubleTypeBySymbol(value: String): DoubleType {
                return ImmutableList.of<DoubleType>(PERCENT, SLASH).stream()
                    .filter { x: DoubleType -> value.contains(x.symbol) }
                    .findFirst()
                    .orElse(OTHER)
            }
        }
    }

    companion object {
        private const val ACTORS_SPLIT_OPERATOR = ","
        private const val UNKNOWN_CURRENCY_CODE = "UNKNOWN_CURRENCY_CODE"
    }
}