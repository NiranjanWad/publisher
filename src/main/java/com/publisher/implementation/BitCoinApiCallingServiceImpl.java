package com.publisher.implementation;

import com.publisher.dao.BitCoinApiCallingService;
import com.publisher.model.BitCoinEvaluation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class BitCoinApiCallingServiceImpl implements BitCoinApiCallingService {


    private static final Logger log = LoggerFactory.getLogger(BitCoinApiCallingServiceImpl.class);

    @Override
    @Scheduled(fixedDelay = 10000, initialDelay = 500)
    public void callBitCoinApi() {
        RestTemplate rest = new RestTemplate();
        BitCoinEvaluation[] coins = rest.getForObject("https://api.coinmarketcap.com/v1/ticker/", BitCoinEvaluation[].class);

        Object[] btc = Arrays.stream(coins).filter(x -> x.getName().equalsIgnoreCase("bitcoin")).toArray();
        log.info("Name :" + ((BitCoinEvaluation) btc[0]).getName());

        log.info(" Price USD :" + Double.valueOf(((BitCoinEvaluation) btc[0]).getPrice_usd()));

        Object[] cash = Arrays.stream(coins).filter(name -> name.getId().equalsIgnoreCase("bitcoin-cash")).toArray();
        log.info("Bitcoin Cash: " +((BitCoinEvaluation) cash[0]).getPercent_change_7d());
    }
}
