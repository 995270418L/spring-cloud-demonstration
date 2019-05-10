package com.ybwx.common.mysql.generator.bean;

import lombok.Data;

import java.util.List;
import java.util.Set;


/**
 * @author pst
 */
@Data
public class Table {

    private Column columnKey;
    private Column columnKeyCollection;
    private List<Column> columnList;
    private Set<String> dataTypeSet;

    public Table() {
        super();
    }

    public Table(Column columnKey,Column columnKeyCollection,  List<Column> columnList, Set<String> dataTypeSet) {
        super();
        this.columnKey = columnKey;
        this.columnKeyCollection = columnKeyCollection;
        this.columnList = columnList;
        this.dataTypeSet = dataTypeSet;
    }

}
