package com.hedera.exchange.exchanges;

/*-
 * ‌
 * Hedera Exchange Rate Tool
 * ​
 * Copyright (C) 2019 - 2020 Hedera Hashgraph, LLC
 * ​
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ‍
 */

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BinanceTestCases {
    @Test
    public void retrieveBinanceDataTest() throws Exception {
        final String urlString = "https://api.binance.us/api/v3/ticker/24hr?symbol=HBARUSD";
        final String result = "{\"quoteVolume\":\"1631.03198900\", \"lastPrice\":\"0.0429\"}";

        final InputStream json = new ByteArrayInputStream(result.getBytes());
        final HttpURLConnection connection = mock(HttpURLConnection.class);
        when(connection.getInputStream()).thenReturn(json);

        final CoinFactory factory = new CoinFactory(connection);
        final Binance binance = factory.load(urlString, Binance.class);
        assertEquals((Double)0.0429, binance.getHBarValue());
        assertEquals((Double)1631.03198900, binance.getVolume());
    }
}
