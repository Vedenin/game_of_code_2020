package eu.gameofcode.endgame.controllers;

import com.github.vedenin.atoms.web_parsers.downloader.DownloaderIAtom;
import com.github.vedenin.atoms.web_parsers.downloader.data.HTMLRawAtomData;
import com.github.vedenin.atoms.web_parsers.downloader.impl.DownloaderSimpleAtom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static eu.gameofcode.endgame.controllers.HeatbeatController.HEAT_BEAT_RESPONSE;

@RestController
public class CheckHeatbeatController {
    private final DownloaderIAtom downloaderIAtom = DownloaderSimpleAtom.create();
    @Value("${servers.prod.urls.heatbeat}")
    private String heatbeatUrl;

    @RequestMapping("/check_heatbeat")
    public String getCheckHeatBeat() {
        String[] urls = heatbeatUrl.split(";");
        String result = appendHeader("");
        for(String url: urls) {
            result = appendUrl(result, url);
            final HTMLRawAtomData htmlResult = downloaderIAtom.getStringFromUrl(url);
            result = appendStatus(result, htmlResult);
        }
        return result;
    }


    private static String appendHeader(final String resultString) {
        return resultString + "<h1>Statuses</h1>";
    }

    private static String appendUrl(final String resultString, final String url) {
        return resultString + "<p>" + url + " = ";
    }

    private static String appendStatus(final String resultString, final HTMLRawAtomData htmlResult) {
        final String result;
        if(htmlResult.isSuccess() && HEAT_BEAT_RESPONSE.equals(htmlResult.getHtml())) {
            result = "LIVE";
        } else {
            result = "DIED. Error " + htmlResult.getErrorMessage() + ". Html " + htmlResult.getHtml();
        }
        return resultString + result + "</p>";
    }
}
