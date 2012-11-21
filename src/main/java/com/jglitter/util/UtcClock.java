package com.jglitter.util;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Encapsulates a clock whose dates are UTC based.
 */
@Component
public class UtcClock implements Clock {

    @Override
    public Date now() {
        return new Date();
    }
}