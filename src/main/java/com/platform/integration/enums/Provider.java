package com.platform.integration.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Provider {
    LOCAL("local"),
    GOOGLE("google"),
    FACEBOOK("facebook");

    private final String value;

    public static Provider fromString(String value) {
        Provider requiredProvider = null;
        for (Provider provider : values()) {
            if (provider.value.equalsIgnoreCase(value)) {
                requiredProvider = provider;
                break;
            }
        }
        return requiredProvider;
    }
}
