package com.sewista;

public interface AdminAdapterListener {

    void onUpdate(Pattern pattern, int position);
    void onDelete(Pattern pattern, int position);
}
