package views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by riven_chris on 15/5/2.
 */
public class CustomLayout extends ViewGroup {

    public CustomLayout(Context context) {
        super(context);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static class CustomLayoutParams extends MarginLayoutParams {
        public CustomLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public CustomLayoutParams(int width, int height) {
            super(width, height);
        }

        public CustomLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public CustomLayoutParams(LayoutParams source) {
            super(source);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new CustomLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new CustomLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof CustomLayoutParams;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if (getChildCount() > 0) {
//            measureChildren(widthMeasureSpec, heightMeasureSpec);
//        }

        // 声明临时变量存储父容器的期望值
        int parentDesireWidth = 0;
        int parentDesireHeight = 0;

    /*
     * 如果有子元素
     */
        if (getChildCount() > 0) {
            // 那么遍历子元素并对其进行测量
            for (int i = 0; i < getChildCount(); i++) {

                // 获取子元素
                View child = getChildAt(i);

                // 获取子元素的布局参数
                CustomLayoutParams clp = (CustomLayoutParams) child.getLayoutParams();

                // 测量子元素并考虑外边距
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

                // 计算父容器的期望值
                parentDesireWidth += child.getMeasuredWidth() + clp.leftMargin + clp.rightMargin;
                parentDesireHeight += child.getMeasuredHeight() + clp.topMargin + clp.bottomMargin;
            }

            // 考虑父容器的内边距
            parentDesireWidth += getPaddingLeft() + getPaddingRight();
            parentDesireHeight += getPaddingTop() + getPaddingBottom();

            // 尝试比较建议最小值和期望值的大小并取大值
            parentDesireWidth = Math.max(parentDesireWidth, getSuggestedMinimumWidth());
            parentDesireHeight = Math.max(parentDesireHeight, getSuggestedMinimumHeight());
        }

        // 设置最终测量值O
        setMeasuredDimension(resolveSize(parentDesireWidth, widthMeasureSpec), resolveSize(parentDesireHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int parentPaddingLeft = getPaddingLeft();
        int parentPaddingTop = getPaddingTop();

        if (getChildCount() > 0) {
            int height = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);

                CustomLayoutParams clp = (CustomLayoutParams) child.getLayoutParams();

//                child.layout(0, height, child.getMeasuredWidth(), child.getMeasuredHeight() + height);

                child.layout(parentPaddingLeft + clp.leftMargin, height + parentPaddingTop + clp.topMargin, child.getMeasuredWidth() + parentPaddingLeft + clp.leftMargin, child.getMeasuredHeight() + height + parentPaddingTop + clp.topMargin);

                height += child.getMeasuredHeight() + clp.topMargin + clp.bottomMargin;
            }
        }

    }
}
