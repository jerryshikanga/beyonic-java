package com.beyonic.models;

import com.beyonic.exceptions.BeyonicException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by jerryshikanga on  2019-07-07
 */
public class Transaction extends BaseModel {
    public Transaction() throws BeyonicException {
        super("transactions");
    }

    @Override
    public String create(@NotNull HashMap<String, Object> payload, HashMap<String, String> headers) throws BeyonicException {
        throw new BeyonicException("Object " + this.getClass().getSimpleName() + "cannot be created via API.");
    }
}
