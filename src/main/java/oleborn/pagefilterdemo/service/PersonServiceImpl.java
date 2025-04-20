package oleborn.pagefilterdemo.service;

import lombok.RequiredArgsConstructor;
import oleborn.pagefilterdemo.mapper.PersonMapper;
import oleborn.pagefilterdemo.model.Person;
import oleborn.pagefilterdemo.model.RequestDto;
import oleborn.pagefilterdemo.model.ResponseDto;
import oleborn.pagefilterdemo.repository.PersonRepository;
import oleborn.pagefilterdemo.specification.PersonSpecification;
import oleborn.pagefilterdemo.util.RandomStringGenerator;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import oleborn.pagefilterdemo.model.PersonDto;

import java.util.List;
import java.util.Random;


@Service
@Transactional
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;


    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person savedPerson = personRepository.save(personMapper.toEntity(personDto));
        return personMapper.toDto(savedPerson);
    }

    @Override
    public String createRandomPersons(int count) {
        for (int i = 0; i < count; i++) {
            PersonDto personDto = PersonDto.builder()
                    .name(RandomStringGenerator.generateRandomName(5))
                    .surname(RandomStringGenerator.generateRandomName(8))
                    .age(new Random().nextInt(100))
                    .sex("Male")
                    .numberPassport(new Random().nextInt(100000))
                    .build();
            createPerson(personDto);
        }

        return "Успешно создано: "+ count +" сущностей в БД";
    }

    @Override
    public List<PersonDto> findAllPersons() {
        List<Person> all = personRepository.findAll();
        return all.stream().map(personMapper::toDto).toList();
    }

    @Override
    public Page<Person> findAllPersonsPage(int pageNumber, int pageSize, String sortField, String sortDirection) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(sortDirection), sortField));

        return personRepository.findAll(pageable);
    }

    @Override
    public ResponseDto findAllPersonsPageDto(int pageNumber, int pageSize, String sortField, String sortDirection) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(sortDirection), sortField));

        Page<Person> all = personRepository.findAll(pageable);

        return new ResponseDto(
                all.getContent().stream().map(personMapper::toDto).toList(),
                all.getPageable().getPageNumber(),
                all.getPageable().getPageSize(),
                all.getTotalPages(),
                all.getTotalElements()
        );
    }

    @Override
    public ResponseDto findAllPersonsPageDtoFil(RequestDto requestDto) {

        Pageable pageable = PageRequest.of(requestDto.page(),
                requestDto.size(),
                Sort.by(Sort.Direction.fromString(requestDto.sortDirection()), requestDto.sortField()));

        Specification<Person> spec = PersonSpecification.personSpecification(
                requestDto.numberPassport(),
                requestDto.name(),
                requestDto.surname(),
                requestDto.age(),
                requestDto.sex(),
                requestDto.startAge(),
                requestDto.finishAge()
        );

        Page<Person> personPage = personRepository.findAll(spec, pageable);

        return new ResponseDto(
                personPage.getContent().stream().map(personMapper::toDto).toList(),
                personPage.getPageable().getPageNumber(),
                personPage.getPageable().getPageSize(),
                personPage.getTotalPages(),
                personPage.getTotalElements()
        );
    }
}
