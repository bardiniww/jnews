package com.app.jnews.scheduller;

import com.app.jnews.model.News;
import com.app.jnews.service.NewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParseNewsTask {

    private static final String URL = "https://news.ycombinator.com/";

    private static final String USER_AGENT = "Mozilla";

    private static final String REFERRER = "https://google.com";

    private static final String ELEMENT_CLASS = "storylink";

    @Autowired
    private NewsService newsService;

    @Scheduled(fixedDelay = 10000)
    public void parseNewNews() {
        try {
            final Document document = Jsoup.connect(URL)
                    .userAgent(USER_AGENT)
                    .timeout(5000)
                    .referrer(REFERRER)
                    .get();

            document.getElementsByClass(ELEMENT_CLASS).forEach(el -> {
                String title = el.ownText();
                if (!newsService.isExist(title)) {
                    News news = new News();
                    news.setTitle(title);
                    newsService.save(news);
                }
            });

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
