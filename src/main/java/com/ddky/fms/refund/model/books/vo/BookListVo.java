package com.ddky.fms.refund.model.books.vo;

import com.ddky.fms.refund.model.books.entry.BookInfo;
import lombok.Data;

import java.util.List;

/**
 * @author a
 */
@Data
public class BookListVo {
    private List<BookInfo> bookInfoList;
}
