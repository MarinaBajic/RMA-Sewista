package com.sewista.recycler;

import com.sewista.model.Pattern;

public interface AdminAdapterListener {

    void onUpdate(Pattern pattern, int position);
    void onDelete(Pattern pattern, int position);
}
