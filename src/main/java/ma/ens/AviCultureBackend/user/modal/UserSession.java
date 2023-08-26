package ma.ens.AviCultureBackend.user.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_session")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserSession {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", columnDefinition = "VARCHAR(255)")
	private String id;

	@Column(name = "start_date")
	private final LocalDateTime startDate = LocalDateTime.now();

	@Column(name = "end_date")
	private LocalDateTime endTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public boolean isStillValid() {
		return this.endTime == null ||
				this.endTime.isAfter(LocalDateTime.now());
	}
}
