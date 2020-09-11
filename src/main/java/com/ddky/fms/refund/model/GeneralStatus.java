package com.ddky.fms.refund.model;

/**
 * @author a
 * 系统中的所有处理状态
 */
public enum GeneralStatus {
    //公共状态
    success(0, "处理成功", "success"),
    query_success(0, "查询成功", "Query success"),
    update_success(0, "修改成功", "Modify the success"),
    delete_success(0, "删除成功", "Delete the success"),
    insert_success(0, "添加成功", "Insert the success"),
    failure(1, "处理失败", "fail");

    private final int status;
    private final String detail;
    private final String enDetail;

    GeneralStatus(int status, String detail, String enDetail) {
        this.status = status;
        this.detail = detail;
        this.enDetail = enDetail;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public String getEnDetail() {
        return enDetail;
    }

    @Override
    public String toString() {
        return "{status:" + this.status + ",detail:" + this.detail + "}";
    }
}
