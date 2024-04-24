package dto.pet;

import java.util.List;

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
@JsonInclude()
public class PetDTO {

	private long id;
	private CategoryDTO category;
	private String name;
	private List<String> photoUrls;
	private List<TagDTO> tags;
	private String status;
}
