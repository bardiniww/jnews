package com.app.jnews.service;

import com.app.jnews.model.News;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {
    void save(final News news);

    boolean isExist(final String newsTitle);

    List<News> getAllNews();
}
