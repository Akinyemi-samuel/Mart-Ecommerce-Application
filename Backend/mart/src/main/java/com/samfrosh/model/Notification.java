package com.samfrosh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_sequence")
    @SequenceGenerator(
            name = "notification_sequence",
            sequenceName = "notification_sequence",
            initialValue = 100,
            allocationSize = 1
    )
    @Column(name = "notification_id")
    private Long id;

    private LocalDate datePosted;

    private String userId;

    @NotBlank(message = "This field cannot be blank")
    private String message;

    private boolean status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification that)) return false;
        return isStatus() == that.isStatus() && Objects.equals(getId(), that.getId()) && Objects.equals(getDatePosted(), that.getDatePosted()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDatePosted(), getUserId(), getMessage(), isStatus());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
