package com.example.reservation;

import static com.example.coupon.CouponStatus.NOT_USE;
import static com.example.reservation.ReservationStatus.WAITING;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.coupon.Coupon;
import com.example.coupon.CouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    @DisplayName("쿠폰 id와 쿠폰 상태로 예약을 조회한다.")
    void findByCoupon_IdAndCoupon_CouponStatus() {
        Coupon coupon = couponRepository.save(new Coupon(NOT_USE, "호호의 커피 쿠폰"));
        reservationRepository.save(new Reservation(coupon, WAITING));

        Reservation reservation = reservationRepository.findByCoupon_IdAndCoupon_CouponStatus(coupon.getId(), NOT_USE);

        assertThat(reservation.getReservationStatus()).isEqualTo(WAITING);
    }
}
