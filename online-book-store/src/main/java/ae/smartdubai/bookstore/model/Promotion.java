package ae.smartdubai.bookstore.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROMOTION")
public class Promotion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODE")
	private String code;

	@Column(name = "PERCENTAGE")
	private Integer percentage;

	@Column(name = "START_DATE")
	private LocalDateTime startDate;

	@Column(name = "END_DATE")
	private LocalDateTime endDate;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "CODE")
	private Set<Classification> classifications = new HashSet<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	public Set<Classification> getClassifications() {
		return classifications;
	}

	public void setClassifications(Set<Classification> classifications) {
		this.classifications = classifications;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Promotion [code=" + code + ", percentage=" + percentage + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
}
