package com.racartech.library.rctandroid.snippets.recyclerview;

import android.content.Context;
import android.widget.Toast;

public class SnippetPreloadingLinearLayoutManager {

    public static void test(Context app_context){
        Toast.makeText(app_context, "Snippet Class Test", Toast.LENGTH_SHORT).show();
    }
    /*
    NOTE : this is a snippet class intended to help developers to fasten there production
           by providing them easy to use code common code snippets
     */
}



/* This is a "just paste it and used it" code, no need to edit/customize it



import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;


public class PreLoadingLinearLayoutManager extends LinearLayoutManager {
    private int mPages = 1;
    private OrientationHelper mOrientationHelper;

    public PreLoadingLinearLayoutManager(final Context context) {
        super(context);
    }

    public PreLoadingLinearLayoutManager(final Context context, final int pages) {
        super(context);
        this.mPages = pages;
    }

    public PreLoadingLinearLayoutManager(final Context context, final int orientation, final boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void setOrientation(final int orientation) {
        super.setOrientation(orientation);
        mOrientationHelper = null;
    }


    public void setPages(final int pages) {
        this.mPages = pages;
    }

    @Override
    protected int getExtraLayoutSpace(final RecyclerView.State state) {
        if (mOrientationHelper == null) {
            mOrientationHelper = OrientationHelper.createOrientationHelper(this, getOrientation());
        }
        return mOrientationHelper.getTotalSpace() * mPages;
    }


 */

