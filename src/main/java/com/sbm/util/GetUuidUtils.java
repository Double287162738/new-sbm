package com.sbm.util;

import java.util.UUID;

public final class GetUuidUtils {

    private GetUuidUtils() {

    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
