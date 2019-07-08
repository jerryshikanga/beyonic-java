package com.beyonic.models;

import com.beyonic.exceptions.BeyonicException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by jerryshikanga on  2019-07-07
 */
public class Payment extends BaseModel {
    public Payment() throws BeyonicException {
        super("payments");
    }

    @Override
    public String create(@NotNull HashMap<String, Object> payload, HashMap<String, String> headers) throws BeyonicException {
        return super.create(payload, headers);
    }
}
