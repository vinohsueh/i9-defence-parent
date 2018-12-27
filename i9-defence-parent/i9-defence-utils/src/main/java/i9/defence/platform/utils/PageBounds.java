package i9.defence.platform.utils;

import java.util.ArrayList;
import java.util.List;

public class PageBounds<T> {

    private int currectPage;

    private int pageSize = 1;

    private int totalSize;

    private int totalPage;

    private List<T> pageList;

    public int getOffset() {
        return (getCurrectPage() - 1) * getPageSize();
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public int getCurrectPage() {
        return currectPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageBounds(int currectPage, int totalSize, int pageSize) {
        this.currectPage = currectPage;
        this.totalSize = totalSize;
        this.pageSize = pageSize;
    }

    public PageBounds(int currectPage, int totalSize) {
        this.currectPage = currectPage;
        this.totalSize = totalSize;
    }

    public int getTotalPage() {
        totalPage = totalSize / pageSize;
        if (totalSize % pageSize != 0)
            totalPage++;
        return totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public boolean isHasFirst() {
        if (currectPage == 1) {
            return false;
        }
        return true;
    }

    public boolean isHasLast() {
        if (currectPage == getTotalPage()) {
            return false;
        }
        return true;
    }

    public boolean isHasNext() {
        if (isHasLast()) {
            return true;
        }
        return false;
    }

    public boolean isHasPrevious() {
        if (isHasFirst()) {
            return true;
        }
        return false;
    }

    /**
     * 计算显示当前分页的起始页
     * 
     * @param pageNum   当前页码
     * @param pageCount 总页数
     * @param sideNum   分页系数 分页条中显示几个数字页码。 显示数字页码个数 = 2 * sideNum + 1
     */
    public List<Integer> calcPage(int pageNum, int pageCount, int sideNum) {
        int startNum = 0;
        int endNum = 0;

        if (pageCount <= sideNum) {
            endNum = pageCount;
        } else {
            if ((sideNum + pageNum) >= pageCount) {
                endNum = pageCount;
            } else {
                endNum = sideNum + pageNum;
                if ((sideNum + pageNum) <= (2 * sideNum + 1)) {
                    if ((2 * sideNum + 1) >= pageCount) {
                        endNum = pageCount;
                    } else {
                        endNum = 2 * sideNum + 1;
                    }
                } else {
                    endNum = sideNum + pageNum;
                }
            }
        }
        if (pageNum <= sideNum) {
            startNum = 1;
        } else {
            if ((pageNum + sideNum) >= pageCount) {
                if ((2 * sideNum + 1) >= pageCount) {
                    if ((pageCount - 2 * sideNum) >= 1) {
                        startNum = pageCount - 2 * sideNum;
                    } else {
                        startNum = 1;
                    }
                } else {
                    startNum = pageCount - 2 * sideNum;
                }
            } else {
                if ((pageNum - sideNum) >= 1) {
                    startNum = pageNum - sideNum;
                } else {
                    startNum = 1;
                }
            }
        }
        return loopOutPageNum(startNum, endNum, pageNum);
    }

    /**
     * 输出计算出来的当前分页详情
     * 
     * @param startNum
     * @param endNum
     * @param pageNum
     */
    public List<Integer> loopOutPageNum(int startNum, int endNum, int pageNum) {
        if (startNum == endNum)
            return new ArrayList<Integer>();
        List<Integer> pageNumList = new ArrayList<Integer>();
        for (int i = startNum; i <= endNum; i++) {
            if (i == pageNum) {
                pageNumList.add(i);
            } else {
                pageNumList.add(i);
            }
        }
        return pageNumList;
    }

    public List<Integer> getLoopPageNum() {
        return calcPage(currectPage, getTotalPage(), 3);
    }
}
