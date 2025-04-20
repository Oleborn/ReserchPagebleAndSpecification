package oleborn.pagefilterdemo.mapper;

import oleborn.pagefilterdemo.model.Person;
import oleborn.pagefilterdemo.model.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;


import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    Person toEntity(PersonDto personDto);

    PersonDto toDto(Person person);

    List<PersonDto> toDtoList(List<Person> persons);

}
