package oleborn.pagefilterdemo.service;

import oleborn.pagefilterdemo.model.Person;
import oleborn.pagefilterdemo.model.PersonDto;
import oleborn.pagefilterdemo.model.RequestDto;
import oleborn.pagefilterdemo.model.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface PersonService {

    PersonDto createPerson(PersonDto personDto);

    String createRandomPersons(int count);

    List<PersonDto> findAllPersons();

    Page<Person> findAllPersonsPage(int pageNumber, int pageSize, String sortField, String sortDirection);

    ResponseDto findAllPersonsPageDto(int pageNumber, int pageSize, String sortField, String sortDirection);

    ResponseDto findAllPersonsPageDtoFil(RequestDto requestDto);
}
