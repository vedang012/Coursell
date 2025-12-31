package com.vedang.coursell.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who paid
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // What they paid for
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    // Money stuff
    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String currency; // "INR", "USD"

    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // PENDING, SUCCESS, FAILED, REFUNDED

    // Gateway reference
    @Column(unique = true)
    private String providerPaymentId;

    // Audit
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
