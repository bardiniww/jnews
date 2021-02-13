package com.app.jnews.service;

import com.app.jnews.model.News;
import com.app.jnews.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public void save(final News news) {
        newsRepository.save(news);
    }

    @Override
    public boolean isExist(final String newsTitle) {
        return newsRepository.findAll()
                .stream()
                .map(News::getTitle)
                .anyMatch(t -> t.equals(newsTitle));
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
}
