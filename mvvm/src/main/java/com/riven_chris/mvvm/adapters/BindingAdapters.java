package com.riven_chris.mvvm.adapters;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;
import androidx.databinding.adapters.ListenerUtil;
import androidx.databinding.adapters.ViewBindingAdapter;

import com.riven_chris.mvvm.R;

public class BindingAdapters {

//    @BindingMethods({@BindingMethod(type = "android.widget.ImageView", attribute = "android:tint", method = "setImageTintList"),})
//    public static void setImageUrl() {
//
//    }

    @BindingAdapter("bind:paddingLeft")
    public static void setPaddingLeft(View view, int padding) {
        view.setPadding(padding,
                view.getPaddingTop(),
                view.getPaddingRight(),
                view.getPaddingBottom());
    }

    //Binding adapter methods may optionally take the old values in their handlers.
    // A method taking old and new values should declare all old values for the attributes first,
    // followed by the new values
    @BindingAdapter("bind:paddingLeft")
    public static void setPaddingLeft(View view, int oldPadding, int newPadding) {
        if (oldPadding != newPadding) {
            view.setPadding(newPadding,
                    view.getPaddingTop(),
                    view.getPaddingRight(),
                    view.getPaddingBottom());
        }
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        view.setImageDrawable(error);
    }

    //The adapter is called if both imageUrl and error are used for an ImageView object and imageUrl is a string and error
    // is a Drawable. If you want the adapter to be called when any of the attributes is set, you can set the optional
    // requireAll flag of the adapter to false
    @BindingAdapter(value = {"imageUrl", "placeholder"}, requireAll = false)
    public static void setImageUrl(ImageView imageView, String url, Drawable placeHolder) {
        if (url == null) {
            imageView.setImageDrawable(placeHolder);
        } else {
            loadImage(imageView, url, placeHolder);
        }
    }

    //<View android:onLayoutChange="@{() -> handler.layoutChanged()}"/>
    @BindingAdapter("bind:onLayoutChange")
    public static void setOnLayoutChangeListener(View view, View.OnLayoutChangeListener oldValue,
                                                 View.OnLayoutChangeListener newValue) {
        if (oldValue != null) {
            view.removeOnLayoutChangeListener(oldValue);
        }
        if (newValue != null) {
            view.addOnLayoutChangeListener(newValue);
        }
    }

    @BindingAdapter(value = {"bind:onViewDetachedFromWindow", "android:onViewAttachedToWindow"}, requireAll = false)
    public static void setListener(View view, ViewBindingAdapter.OnViewDetachedFromWindow detach, ViewBindingAdapter.OnViewAttachedToWindow attach) {
        View.OnAttachStateChangeListener newListener;
        if (detach == null && attach == null) {
            newListener = null;
        } else {
            newListener = new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    if (attach != null) {
                        attach.onViewAttachedToWindow(v);
                    }
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    if (detach != null) {
                        detach.onViewDetachedFromWindow(v);
                    }
                }
            };
        }

        View.OnAttachStateChangeListener oldListener = ListenerUtil.trackListener(view, newListener,
                R.id.onAttachStateChangeListener);
        if (oldListener != null) {
            view.removeOnAttachStateChangeListener(oldListener);
        }
        if (newListener != null) {
            view.addOnAttachStateChangeListener(newListener);
        }
    }

    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int color) {
        return new ColorDrawable(color);
    }
}
