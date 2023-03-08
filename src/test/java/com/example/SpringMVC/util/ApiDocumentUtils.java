package com.example.SpringMVC.util;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

// request와 response에 해당하는 문서 영역을 전처리하는 기능을 공통화한 인터페이스
public interface ApiDocumentUtils {
    // 문서에 표시되는 JSON 포멧의 request body를 보기 좋게 표현
    static OperationRequestPreprocessor getRequestPreProcessor(){
        return preprocessRequest(prettyPrint());
    }

    // 문서에 표시되는 JSON 포멧의 response body를 보기 좋게 표현
    static OperationResponsePreprocessor getResponsePreProcessor() {
        return preprocessResponse(prettyPrint());
    }
}
