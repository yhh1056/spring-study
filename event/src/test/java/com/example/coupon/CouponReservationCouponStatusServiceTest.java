package com.example.coupon;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.reservation.Reservation;
import com.example.reservation.ReservationRepository;
import com.example.reservation.ReservationService;
import com.example.reservation.ReservationStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
// 테스트코드간 의존성은 신경쓰지 않는다.
class CouponReservationCouponStatusServiceTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    @DisplayName("쿠폰을 즉시 사용할 경우 예약의 상태가 승인으로 변경된다.")
    void useImmediately() {
        Long couponId = couponService.save("호호의 커피 쿠폰");
        // 예약이 저장되면 에약은 WAITING 상태가 된다.
        Long reservationId = reservationService.save(couponId);

        // 이벤트가 발행된다.
        couponService.useImmediately(couponId);

        // 쿠폰이 즉시 사용됐다면 예약은 ACCEPT 상태로 변경되어야 한다.
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();

        assertThat(reservation.getReservationStatus()).isEqualTo(ReservationStatus.ACCEPT);
    }
}
