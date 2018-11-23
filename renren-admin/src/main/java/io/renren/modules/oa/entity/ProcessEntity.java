package io.renren.modules.oa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.utils.J8DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ProcessEntity implements Serializable {

    /**
     * 流程id
     */
    private String processId;

    /**
     * 申请标题
     */
    @TableField(exist = false)
    private String title;

    /**
     * 申请时间
     */
    @TableField(exist = false)
    @JsonFormat(pattern = J8DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createDateTime;


    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }
}
