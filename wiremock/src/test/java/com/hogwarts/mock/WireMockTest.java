package com.hogwarts.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-12-18 03:01
 * @Version 1.0
 **/
public class WireMockTest {

    static WireMockServer wireMockServer;

    @BeforeAll
    public static void beforeAll() {
        wireMockServer = new WireMockServer(wireMockConfig().port(8888));
        wireMockServer.start();
        configureFor("localhost", 8888);
    }

    @Test
    void mockTest() throws InterruptedException {
        stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>Some content</response>")));

        Thread.sleep(50000);
    }

    @Test
    void proxyMock() throws Exception {
        stubFor(get(urlMatching(".*")).atPriority(10)
                .willReturn(aResponse().proxiedFrom("https://ceshiren.com")));

        stubFor(get(urlEqualTo("/categories_and_latest")).atPriority(10)
                .willReturn(aResponse().withBody(Files.readAllBytes(Paths.get(WireMockTest.class.getResource("/mock.json").getPath())))));

        Thread.sleep(500 * 1000);
    }


}
