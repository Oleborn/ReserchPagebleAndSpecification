package oleborn.pagefilterdemo.controller;

import lombok.RequiredArgsConstructor;
import oleborn.pagefilterdemo.model.Person;
import oleborn.pagefilterdemo.model.PersonDto;
import oleborn.pagefilterdemo.model.RequestDto;
import oleborn.pagefilterdemo.model.ResponseDto;
import oleborn.pagefilterdemo.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/create-count/{count}")
    public ResponseEntity<String> createCount(@PathVariable int count) {
            return ResponseEntity.ok(personService.createRandomPersons(count));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PersonDto>> getAll() {
        return ResponseEntity.ok(personService.findAllPersons());
    }

    @GetMapping("/getAllPages")
    public ResponseEntity<Page<Person>> getAllPages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "numberPassport") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        return ResponseEntity.ok(personService.findAllPersonsPage(page, size, sortField, sortDirection));
    }

    @GetMapping("/getAllDto")
    public ResponseEntity<ResponseDto> getAllDto(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "numberPassport") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        return ResponseEntity.ok(personService.findAllPersonsPageDto(page, size, sortField, sortDirection));
    }

    /**
     * Обрабатывает GET-запрос для получения списка PersonDTO с фильтрацией и пагинацией.
     *
     * <p><b>Как работает {@code @ModelAttribute}:</b></p>
     * <ul>
     *   <li>Автоматически связывает параметры HTTP-запроса с полями объекта {@link RequestDto}.</li>
     *   <li>Поддерживает все типы параметров: query (?name=value), form-data, x-www-form-urlencoded.</li>
     *   <li>Если поле не передано в запросе, оно останется {@code null} (или значением по умолчанию).</li>
     * </ul>
     *
     * <p><b>Пример запроса:</b></p>
     * <pre>{@code
     * GET /getAllDtoFil?page=0&size=10&name=Иван&surname=Иванов
     * }</pre>
     *
     * @param requestDto DTO с параметрами фильтрации и пагинации.
     *                   Поля должны совпадать с параметрами запроса или быть аннотированы {@code @RequestParam}.
     * @return {@link ResponseEntity} с {@link ResponseDto}, содержащим:
     *         <ul>
     *           <li>Отфильтрованный список {@link PersonDTO}</li>
     *           <li>Метаданные пагинации (номер страницы, общее количество элементов и т.д.)</li>
     *         </ul>
     *
     * @see RequestDto
     * @see ResponseDto
     * @see PersonService#findAllPersonsPageDtoFil(RequestDto)
     */

    @GetMapping("/getAllDtoFil")
    public ResponseEntity<ResponseDto> getAllDtoFil(@ModelAttribute RequestDto requestDto) {
        return ResponseEntity.ok(personService.findAllPersonsPageDtoFil(requestDto));
    }


}
