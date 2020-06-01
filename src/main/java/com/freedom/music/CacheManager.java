package com.freedom.music;

import com.freedom.music.datasource.repository.ResponseCodeTranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static com.freedom.music.common.constants.StringConstants.SOME_ERROR_OCCURRED;

@Component
public class CacheManager {

    private Map<String, String> responseCodeTranslatorCache = new HashMap<>();

    private final ResponseCodeTranslatorRepository responseCodeTranslatorRepository;

    @Autowired
    public CacheManager(ResponseCodeTranslatorRepository responseCodeTranslatorRepository) {
        this.responseCodeTranslatorRepository = responseCodeTranslatorRepository;
    }

    @Scheduled(fixedDelay = 10000)
    private void refreshRCT() {
        Map<String, String> responseCodeTranslatorCacheTemp = new HashMap<>();
        responseCodeTranslatorRepository
                .findAll()
                .forEach(rct -> responseCodeTranslatorCacheTemp.put(rct.getRespConstant(), rct.getRespCode()));
        responseCodeTranslatorCache = responseCodeTranslatorCacheTemp;
    }

    public String findRespCodeByRespConstant(String respConstant) {
        return responseCodeTranslatorCache.getOrDefault(respConstant, SOME_ERROR_OCCURRED);
    }

    @PostConstruct
    public void refreshCacheManager() {
        refreshRCT();
    }
}