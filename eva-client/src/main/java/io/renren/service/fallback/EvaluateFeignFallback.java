package io.renren.service.fallback;

import io.renren.common.utils.R;
import io.renren.service.EvaluteFeignService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EvaluateFeignFallback implements EvaluteFeignService {
    @Override
    public R evalPaper(Integer evalId) {
        return R.error(408, "连接超时，无法取得测评问卷数据！");
    }

    @Override
    public R saveEval(Map<String, Object> resultMap) {
        return R.error(408, "连接超时，无法提交测评问卷数据！");
    }
}
