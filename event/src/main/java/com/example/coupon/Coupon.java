package com.example.coupon;

import com.example.common.Events;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private CouponStatus couponStatus;

    private String content;

    public Coupon(final Long id, final CouponStatus couponStatus, final String content) {
        this.id = id;
        this.couponStatus = couponStatus;
        this.content = content;
    }

    public Coupon(final CouponStatus couponStatus, final String content) {
        this(null, couponStatus, content);
    }

    public void use() {
        this.couponStatus = CouponStatus.USED;
        Events.publish(new CouponUsedEvent(id));
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", couponStatus=" + couponStatus +
                ", content='" + content + '\'' +
                '}';
    }
}
