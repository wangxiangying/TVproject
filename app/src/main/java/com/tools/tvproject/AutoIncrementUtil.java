package com.tools.tvproject;

import android.animation.ValueAnimator;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

public class AutoIncrementUtil {

    public static final String FLOATTYPE="FloatType";
    public static final String INTTYPE="IntType";


    public static void startAnimation(String type,final TextView tvView,float floatValue
            ,boolean isRoundUp,final String danwei,int duration){
        ValueAnimator animator=null;
        if(type.equals(FLOATTYPE)){
            animator=ValueAnimator.ofFloat(0,floatValue);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float curValue = (float)valueAnimator.getAnimatedValue();
                    tvView.setText(NumUtil.FormatFloat(curValue)+danwei);
                }
            });
        }else if(type.equals(INTTYPE)){
            String targetValueString =NumUtil.FormatRoundUp(isRoundUp,floatValue);
            animator=ValueAnimator.ofInt(0,Integer.parseInt(targetValueString));
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int curValue = (int)valueAnimator.getAnimatedValue();
                    tvView.setText(curValue+danwei);
                }
            });
        }
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

}