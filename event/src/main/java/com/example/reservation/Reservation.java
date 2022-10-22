package com.example.reservation;

import com.example.coupon.Coupon;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    @Enumerated(value = EnumType.STRING)
    private ReservationStatus reservationStatus;

    public Reservation(final Long id, final Coupon coupon, final ReservationStatus reservationStatus) {
        this.id = id;
        this.coupon = coupon;
        this.reservationStatus = reservationStatus;
    }

    public Reservation(final Coupon coupon, final ReservationStatus reservationStatus) {
        this(null, coupon, reservationStatus);
    }

    public void use() {
        this.reservationStatus = ReservationStatus.ACCEPT;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationStatus=" + reservationStatus +
                '}';
    }
}
