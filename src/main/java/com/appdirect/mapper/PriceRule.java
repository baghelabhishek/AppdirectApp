package com.appdirect.mapper;

import com.appdirect.model.PriceCollection;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Function;

public interface PriceRule {

    Function<Set<PriceCollection>, Double> AveragePriceRule = prices ->
            prices.stream()
                    .mapToDouble(PriceCollection::getStorePrice)
                    .average()
                    .orElse(0.0);

    Function<Set<PriceCollection>, Double> LowestPriceRule = prices ->
            prices.stream()
                    .mapToDouble(PriceCollection::getStorePrice)
                    .min()
                    .orElse(0.0);

    Function<Set<PriceCollection>, Double> HighestPriceRule = prices ->
            prices.stream()
                    .mapToDouble(PriceCollection::getStorePrice)
                    .max()
                    .orElse(0.0);

    Function<Set<PriceCollection>, Double> IdealPriceRule = prices -> {
        if (prices.size() > 5) {
            Double average = prices.stream()
                    .sorted(Comparator.comparing(PriceCollection::getStorePrice))
                    .skip(2)
                    .limit(prices.size() - 4)
                    .mapToDouble(PriceCollection::getStorePrice)
                    .average()
                    .orElse(0.0);
            return average + average / 100 * 20;
        }
        return 0.0;
    };
}
