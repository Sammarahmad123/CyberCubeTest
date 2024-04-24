package dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonInclude;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
@JsonInclude
public class OrderDTO {

	private long id;
	private long petId;
	private int quantity;
	private String shipDate;
	private String status;
	private boolean complete;
}
