package com.neusoft.util.store.eap;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.List;

public class DCriteriaPageSupport<T> extends ArrayList<T> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9045252133763507997L;

	public final static int I_PAGE_SIZE = 20;

    private int _page_size = I_PAGE_SIZE;

    private int totalCount;

    private int[] indexes = new int[0];

    private int startIndex = 0;

    /**
     * ArrayList
     */
    public DCriteriaPageSupport(List list)
    {
        super(list);
    }
    /**
     * ����ҳ��
     * @param totalCount int
     */
    public DCriteriaPageSupport(List list ,int totalCount) {
        super(list) ;
        set_page_size(I_PAGE_SIZE);
        setTotalCount(totalCount);
        setStartIndex(0);
    }

    /**
     * ����ҳ��
     * @param totalCount int
     */

    public DCriteriaPageSupport(List list ,int totalCount, int startIndex) {
        super(list) ;
        set_page_size(I_PAGE_SIZE);
        setTotalCount(totalCount);
        setStartIndex(startIndex);
    }

    /**
     * ����ҳ��
     * @param totalCount int
     */

    public DCriteriaPageSupport(List list,int totalCount, int pageSize, int startIndex) {
        super(list) ;
        set_page_size(pageSize);
        setTotalCount(totalCount);
        setStartIndex(startIndex);
    }

    /**
     * �ܼ�¼��
     * @return int
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * �ܼ�¼�� �� ͬʱ������ҳ�� �� ����ÿҳ����ʼλ��
     * @param totalCount int
     */
    public void setTotalCount(int totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
            int count = totalCount / _page_size;
            if (totalCount % _page_size > 0)
                count++;
            indexes = new int[count];
            for (int i = 0; i < count; i++) {
                indexes[i] = _page_size * i;
            }
        } else {
            this.totalCount = 0;
        }
    }

    /**
     * ���ҳ��Ϣ
     * @return int[]
     */
    public int[] getIndexes() {
        return indexes;
    }

    /**
     * ҳ��Ϣ
     * @param indexes int[]
     */
    public void setIndexes(int[] indexes) {
        this.indexes = indexes;
    }

    public int getStartIndex() {
        return startIndex;
    }
    /**
     * ҳ size
     * @return int
     */
    public int get_page_size() {
        return _page_size;
    }
    /**
     * ��ʼ��¼λ��
     * @param startIndex int
     */
    public void setStartIndex(int startIndex) {
        if (totalCount <= 0)
            this.startIndex = 0;
        else if (startIndex >= totalCount)
            this.startIndex = indexes[indexes.length - 1];
        else if (startIndex < 0)
            this.startIndex = 0;
        else {
            this.startIndex = indexes[startIndex / _page_size];
        }
    }

    public void set_page_size(int _page_size) {
        this._page_size = _page_size;
    }
    /**
     * ��һҳ
     * @return int
     */
    public int getNextIndex() {
        int nextIndex = getStartIndex() + _page_size;
        if (nextIndex >= totalCount)
            return getStartIndex();
        else
            return nextIndex;
    }

    /**
     * ��һҳ
     * @return int
     */
    public int getPreviousIndex() {
        int previousIndex = getStartIndex() - _page_size;
        if (previousIndex < 0)
            return 0;
        else
            return previousIndex;
    }
    public String toString()
    {
        return super.toString() ;
    }
}
