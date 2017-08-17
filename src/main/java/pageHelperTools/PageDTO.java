/**
 *
 */
package pageHelperTools;

import java.util.List;

/**
 * @author CHEN XINGXING
 * jquery数据传输对象
 */
public class PageDTO<T> {
    /**
     * 每一行的数据对象
     */
    private List<T> rows;
    /**
     * 总行数
     */
    private int total;

    /**
     * @param rows  每一行的对象数据
     * @param total 数据总数
     */
    public PageDTO(List<T> rows, int total) {
        this.rows = rows;
        this.total = total;
    }

    /**
     * 默认构造
     */
    public PageDTO() {

    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
