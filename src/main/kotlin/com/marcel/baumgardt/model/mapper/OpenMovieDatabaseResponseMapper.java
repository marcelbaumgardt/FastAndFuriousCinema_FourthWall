package com.marcel.baumgardt.model.mapper;

import com.marcel.baumgardt.model.connector.OpenMovieDatabaseRating;
import com.marcel.baumgardt.model.connector.OpenMovieDatabaseResponse;
import com.marcel.baumgardt.model.dto.MovieDetail;
import kotlin.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OpenMovieDatabaseResponseMapper {

    private final static String ACTORS_SPLIT_OPERATOR = ",";
    private final static String UNKNOWN_CURRENCY_CODE = "UNKNOWN_CURRENCY_CODE";

    public MovieDetail mapToMovieDetail(OpenMovieDatabaseResponse openMovieDatabaseResponse) {
        Pair<String, Long> boxOfficePairCurrencyValue = mapToBoxOfficePairCurrencyValue(openMovieDatabaseResponse.getBoxOffice());
        return new MovieDetail(
                openMovieDatabaseResponse.getTitle(),
                openMovieDatabaseResponse.getWriter(),
                mapToListOfActors(openMovieDatabaseResponse.getActors()),
                mapToAverageRatingScore(openMovieDatabaseResponse.getRatings()),
                Integer.parseInt(openMovieDatabaseResponse.getMetaScore()),
                boxOfficePairCurrencyValue.getSecond(),
                boxOfficePairCurrencyValue.getFirst()
        );
    }


    private Pair<String, Long> mapToBoxOfficePairCurrencyValue(String boxOffice) {
        //TODO FTHW-100 - Upgarde
        String currencySymbol = boxOffice.substring(0, 1);
        String currencyCode = Currency.getAvailableCurrencies().stream()
                .filter(c -> c.getSymbol().equals(currencySymbol))
                .map(Currency::getCurrencyCode)
                .findFirst()
                .orElse(UNKNOWN_CURRENCY_CODE);

        Long value = Long.valueOf(boxOffice.substring(1).replace(",", ""));

        return new Pair<>(currencyCode, value);
    }


    private double mapToAverageRatingScore(List<OpenMovieDatabaseRating> ratings) {
        return ratings.stream()
                .mapToDouble(x -> unifyDoubleValue(x.getValue()))
                .average()
                .orElse(0.0);
    }

    private double unifyDoubleValue(String value) {
        //TODO FTHW-100 - Upgarde
        DoubleType doubleType = DoubleType.getDoubleTypeBySymbol(value);
        switch (doubleType) {
            case PERCENT:
                return Double.parseDouble(value.replace(DoubleType.PERCENT.symbol, "")) / 100.00;
            case SLASH:
                List<Double> values = Arrays.stream(value.split(DoubleType.SLASH.symbol))
                        .map(Double::parseDouble)
                        .collect(Collectors.toList());

                return values.get(0) / values.get(1);
            default:
                return 0.0;
        }
    }

    private Set<String> mapToListOfActors(String actors) {
        return Arrays.stream(actors.split(ACTORS_SPLIT_OPERATOR))
                .map(String::trim)
                .collect(Collectors.toSet());
    }

    @Getter
    @AllArgsConstructor
    enum DoubleType {
        //TODO FTHW-100 - Upgarde
        PERCENT("%"),
        SLASH("/"),
        OTHER("");

        private final String symbol;

        public static DoubleType getDoubleTypeBySymbol(String value) {
            return List.of(PERCENT, SLASH).stream()
                    .filter(x -> value.contains(x.symbol))
                    .findFirst()
                    .orElse(OTHER);
        }
    }
}
