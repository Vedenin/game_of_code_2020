package eu.gameofcode.endgame.controllers;

import eu.gameofcode.endgame.controllers.entiry.MetricsData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MetricsRestController {
    @RequestMapping("/metrics")
    public List<MetricsData> getAllMetrics() {
        MetricsData data = new MetricsData(
                50.0188907,
                5.9640793,
                "https://persist.lu/ark:/70795/ghkz8q/articles/DTL43",
                "28 Kleinhoscheid",
                "Language (machine learning):",
                ""
        );
        return Arrays.asList(
                data
        );
    }
}
