package com.freedom.music.datasource.repository;

import com.freedom.music.datasource.model.ResponseCodeTranslator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ResponseCodeTranslatorRepository extends JpaRepository<ResponseCodeTranslator, Long>, JpaSpecificationExecutor<ResponseCodeTranslator> {

}