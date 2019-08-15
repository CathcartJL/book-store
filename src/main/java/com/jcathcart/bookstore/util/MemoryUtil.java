package com.jcathcart.bookstore.util;

import org.springframework.stereotype.Component;

@Component
public class MemoryUtil {

    private Runtime runtime;

    public MemoryUtil() {
        this.runtime = Runtime.getRuntime();
    }

    public String getStats() {
        String stats;

        stats = String.format("Total memory: %smb, Free memory: %smb, Usage: %smb",
                Math.round(runtime.totalMemory() / 1e6),
                Math.round(runtime.freeMemory() / 1e6),
                Math.round((runtime.totalMemory() - runtime.freeMemory()) / 1e6));

        return stats;
    }
}
