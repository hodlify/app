package ru.nikitazhelonkin.coinbalance.data.system;


import android.content.Context;
import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.PluralsRes;
import androidx.annotation.StringRes;

import javax.inject.Inject;

public class ResourceManager {

    private Context mContext;

    @Inject
    public ResourceManager(Context context){
        mContext = context;
    }

    @NonNull
    public String getString(@StringRes int resId){
        return mContext.getString(resId);
    }

    @NonNull
    public String getString(@StringRes int resId, Object... formatArgs){
        return mContext.getString(resId, formatArgs);
    }

    @NonNull
    public String getQuantityString(@PluralsRes int id, int quantity, Object... formatArgs){
        return mContext.getResources().getQuantityString(id, quantity, formatArgs);
    }

    @NonNull
    public String[] getStringArray(@ArrayRes int id){
        return mContext.getResources().getStringArray(id);
    }
}
