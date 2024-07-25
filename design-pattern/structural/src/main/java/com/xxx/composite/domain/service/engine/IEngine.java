package com.xxx.composite.domain.service.engine;



import com.xxx.composite.domain.model.aggregates.TreeRich;
import com.xxx.composite.domain.model.vo.EngineResult;

import java.util.Map;

public interface IEngine {

    EngineResult process(final Long treeId, final String userId, TreeRich treeRich, final Map<String, String> decisionMatter);

}
