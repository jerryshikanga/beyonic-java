package com.beyonic.models;

import com.beyonic.exceptions.BeyonicException;

/**
 * Created by jerryshikanga on  2019-07-07
 */
public class Account extends BaseModel {
    public Account() throws BeyonicException {
        super("accounts");
    }
}
