/**
 * Represents the payload for server status response.
 * Indicates whether the server is running.
 */
package com.ejbank.api.server.payload;

public class ServerPayload {

    private final boolean result;

    public ServerPayload(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }
}
