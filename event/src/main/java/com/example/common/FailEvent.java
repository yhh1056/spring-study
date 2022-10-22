package com.example.common;

import com.example.coupon.CouponUsedEvent;
import lombok.Getter;

@Getter
public class FailEvent {

    private final CouponUsedEvent event;
    private boolean published;

    public FailEvent(final CouponUsedEvent event) {
        this.event = event;
        published = false;
    }

    public void isPublish() {
        this.published = true;
    }
}
